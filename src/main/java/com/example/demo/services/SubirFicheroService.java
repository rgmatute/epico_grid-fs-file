package com.example.demo.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.services.dto.GridFSFileDTO;
import com.example.demo.util.Utils;

@Service
public class SubirFicheroService {
	
	private final GridFileService gridFileService;
	
	private final StoreRepository storeRepository;
	
	public SubirFicheroService(StoreRepository storeRepository, GridFileService gridFileService){
		this.storeRepository = storeRepository;
		this.gridFileService = gridFileService;
	}
	
	public Store subirFichero(MultipartFile file) throws IOException {
		
		String resourceId = gridFileService.store(file).toString();
		
		Store store = new Store();
		store.setFilename(file.getOriginalFilename());
		store.setContentType(file.getContentType());
		store.setSize(file.getSize());
		store.setResourceId(resourceId);
		
		return storeRepository.save(store);
	}
	
	public List<Store> buscarTodos() {
		return storeRepository.findAll();
	}
	
	public Optional<Store> buscarFicheroPorId(String id) {
		return storeRepository.findById(id);
	}
	
	public Object viewImage(String id) {
		
		Optional<Store> storeOptional = storeRepository.findById(id);
		
		if(storeOptional.isEmpty()) {
			return null;
		}
		
		Store store = storeOptional.get();
		
		GridFSFileDTO gridFSFileDTO = gridFileService.findById(store.getResourceId());
		
		byte[] fileBytes = null;
		
		fileBytes = Base64.getDecoder().decode(gridFSFileDTO.getFile().getBytes());
		
		return ResponseEntity
				.status(200)
				.header("Content-Type", gridFSFileDTO.getContentType())
				.header("Content-Disposition", "attachment; filename="+gridFSFileDTO.getName())
				.body(fileBytes);
		
		
	}

}
