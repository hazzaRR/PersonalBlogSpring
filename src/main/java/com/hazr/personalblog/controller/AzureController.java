package com.hazr.personalblog.controller;

import com.hazr.personalblog.service.AzureBlobService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = {"http://localhost:5173", "https://blog.harryredman.com/"})
public class AzureController {

    private final AzureBlobService azureBlobService;

    public AzureController(AzureBlobService azureBlobService) {
        this.azureBlobService = azureBlobService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBlob(@RequestPart String blobName, @RequestPart MultipartFile file) throws IOException {

        String fileName = azureBlobService.upload(blobName, file);
        return ResponseEntity.ok(fileName);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllBlobs() {

        List<String> items = azureBlobService.listBlobs();
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String blobName) {
        boolean deleted = azureBlobService.deleteBlob(blobName);
        if (deleted) {
            return new ResponseEntity<>("Deleted blob " + blobName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Blob " + blobName + " not found", HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/download")
//    public ResponseEntity<ByteArrayResource> getFile
//            (@RequestParam String fileName)
//            throws URISyntaxException {
//
//        ByteArrayResource resource =
//                new ByteArrayResource(azureBlobService
//                        .getFile(fileName));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\""
//                        + fileName + "\"");
//
//        return ResponseEntity.ok().contentType(MediaType
//                        .APPLICATION_OCTET_STREAM)
//                .headers(headers).body(resource);
//    }


    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadBlob(@RequestParam String blobName) {
        byte[] data = azureBlobService.getFile(blobName);
        if (data != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(data.length);
//            headers.set("Content-Disposition", "attachment; filename=" + blobName);
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}