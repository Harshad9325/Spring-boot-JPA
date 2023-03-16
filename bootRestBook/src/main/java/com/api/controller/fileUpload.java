package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.helper.fileUploadhelper;

@RestController
public class fileUpload {
	@Autowired
	private fileUploadhelper fileuploadhelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//	System.out.println(file.getOriginalFilename());
//	System.out.println(file.getSize() );
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());

		try {

// validation
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}

//
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("only jpeg content type are allowed");

			}

//file upload code
			boolean f = fileuploadhelper.uploadFile(file);
			if (f) {
				return ResponseEntity.ok("File  is succesfully uploaded");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Somthing went wrong");
	}
}
