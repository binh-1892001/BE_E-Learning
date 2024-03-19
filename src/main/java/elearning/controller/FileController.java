package elearning.controller;

import elearning.service.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/file")
@RestController
@Secured("ROLE_ADMIN")
public class FileController {
    @Autowired
    private IFileService fileService;

    @Operation(summary = "Upload one file to server")
    @PostMapping("/upload-file")
    ResponseEntity<String> uploadFile(@ModelAttribute MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.uploadFile(file), HttpStatus.OK);
    }

    @Operation(summary = "Upload multiple file to server")
    @PostMapping("/upload-multyFile")
    ResponseEntity<List<String>> uploadMultyFile(MultipartFile [] files) throws IOException {
        return new ResponseEntity<>(fileService.uploadMultyFile(files), HttpStatus.OK);
    }
}
