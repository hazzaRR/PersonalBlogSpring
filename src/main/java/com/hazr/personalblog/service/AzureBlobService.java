package com.hazr.personalblog.service;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AzureBlobService {

    BlobServiceClient blobServiceClient;

    BlobContainerClient blobContainerClient;

    public AzureBlobService(BlobServiceClient blobServiceClient, BlobContainerClient blobContainerClient) {
        this.blobServiceClient = blobServiceClient;
        this.blobContainerClient = blobContainerClient;
    }

    public String upload(String blobName, MultipartFile multipartFile) throws IOException {
        String fileExtension = multipartFile.getOriginalFilename().split("\\.")[1];
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName+fileExtension);
        blobClient.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

        return blobName;
    }

    public byte[] getFile(String fileName) {

        BlobClient blob = blobContainerClient.getBlobClient(fileName);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        blob.download(outputStream);
        //        final byte[] bytes = outputStream.toByteArray();
        final byte[] bytes = blob.downloadContent().toBytes();
//        final byte[] bytes = outputStream.toByteArray();
        return bytes;

    }

    public List<String> listBlobs() {

        List<String> blobNames = new ArrayList<String>();
        for (BlobItem item : blobContainerClient.listBlobs()) {
            blobNames.add(item.getName());
        }
        return blobNames;

    }

    public Boolean deleteBlob(String blobName) {

        BlobClient blob = blobContainerClient.getBlobClient(blobName);
        blob.delete();
        return true;
    }

}
