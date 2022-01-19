package com.example.demo.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Store;
import com.example.demo.services.SubirFicheroService;

@RestController
@RequestMapping("api")
public class SubirFicheroController {
	
	private final SubirFicheroService subirFicheroService;
	
	public SubirFicheroController(SubirFicheroService subirFicheroService){
		this.subirFicheroService = subirFicheroService;
	}
	
	@PostMapping("/store")
	public Store subir(@RequestParam MultipartFile file) throws IOException {
		return subirFicheroService.subirFichero(file);
	}
	
	@GetMapping("/store")
	public List<Store> findAll() {
		return subirFicheroService.buscarTodos();
	}
	
	@GetMapping("/store/view/{id}")
	public ResponseEntity<?> view(@PathVariable String id) {
		
		ResponseEntity<?> result = (ResponseEntity<?>) subirFicheroService.viewImage(id);
		
		return ResponseEntity
		.ok()
		.headers(result.getHeaders())
		.body(result.getBody());
	}


}
