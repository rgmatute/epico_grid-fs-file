package com.example.demo.services;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.dto.GridFSFileDTO;
import com.example.demo.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class GridFileService {
	
	private final Logger log = LoggerFactory.getLogger(GridFileService.class);

	private final GridFsTemplate gridFsTemplate;

	public GridFileService(GridFsTemplate gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
	}
	
	public GridFsTemplate instance() {
        return this.gridFsTemplate;
    }
	
	public GridFSFileDTO findById(String resourceId) {
		
		log.debug("GridFileService->findById({})", resourceId);
		
		GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(resourceId)));
		
		GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
		
		GridFSFileDTO response = new GridFSFileDTO();
		response.setId(gridFSFile.getId().toString());
		response.setName(gridFSFile.getFilename());
		response.setContentType(resource.getContentType());
		response.setFile(Utils.inputStreamToBase64(resource.getContent()));
		
		return response;
	}
	
	public String store(MultipartFile document) {
        InputStream in = null;
        try {
            in = document.getInputStream();
        } catch (IOException e) {
            throw new IllegalArgumentException("El " + document.getOriginalFilename() + " tiene errores, verificar antes de enviar.");
        }
        String fileName = document.getOriginalFilename();
        String contenType = document.getContentType();

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("store-id", "N/A");
        String documentFrontID = gridFsTemplate.store(in, fileName, contenType, basicDBObject).toString();

        return documentFrontID;
    }

}
