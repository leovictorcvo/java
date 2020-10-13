package br.com.leovictorcvo.fileuploader.services;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.leovictorcvo.fileuploader.exception.StorageException;
import br.com.leovictorcvo.fileuploader.exception.StorageFileNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {
	
	private final Path uploadDirectory = Paths.get("uploads");
	
	@Override
	public void init() {
		try {
			Files.createDirectory(uploadDirectory);
		} catch (IOException e) {
			throw new StorageException("Não foi possível criar o diretório de upload", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		Stream<Path> files = null;
		try {
			files = Files.walk(uploadDirectory, 1)
				.filter(path -> !path.equals(uploadDirectory))
				.map(uploadDirectory::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Não foi possível listar os arquivos do diretório de upload", e);
		}
		return files;
	}

	@Override
	public Path load(String filename) {
		return uploadDirectory.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Não foi possível ler o arquivo: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Não foi possível ler o arquivo: " + filename, e);
		}
	}

	@Override
	public String store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = StringUtils.getFilenameExtension(filename);
		String newName;
		try {
			if (file.isEmpty()) {
				throw new StorageException("Não é possível armazenar arquivos vazios " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Nome de arquivo inválido "
								+ filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				String hashed = HashService.randomHex();
				Path path = uploadDirectory.resolve(String.format("%s.%s", hashed, extension));
				Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
				newName = path.getFileName().toString();
			} 
		}
		catch (IOException e) {
			throw new StorageException("Falha ao salvar o arquivo " + filename, e);
		}		
		return newName;
	}
	
	@Override
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(uploadDirectory);
		} catch (IOException e) {
			throw new StorageException("Não foi possível excluir os arquivos do diretório de upload", e);
		}
	}

}
