package br.com.leovictorcvo.fileuploader.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leovictorcvo.fileuploader.services.StorageService;

@RestController
@RequestMapping("/files")
public class FileStorageController {
	@Autowired
	private StorageService storageService;

	@GetMapping
	public List<String> listAll(UriComponentsBuilder uriBuilder) {
		UriComponentsBuilder builder = uriBuilder.path("files/{filename}");
		List<String> files = storageService.loadAll()
				.map(path -> builder.buildAndExpand(path.getFileName().toString()).toUriString())
				.collect(Collectors.toList());
		return files;
	}

	@GetMapping("/{filename}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			UriComponentsBuilder uriBuilder) {

		String filename = storageService.store(file);
		URI uri = uriBuilder.path("/files/{id}").buildAndExpand(filename).toUri();
		return ResponseEntity.created(uri).build();
	}

}
