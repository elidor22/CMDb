package Utilities;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import java.io.IOException;


public class fileUploader {

    public static String url;


   public void upload(String path, String name){
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=cmdbcit;AccountKey=4CMZ33uOXJJDOTh+F2qWrp+0xsdhuUH/UWh5AJmLXO6116wlOtmILtGVuDWtwl8V2FLtkKGWmX/rlVcKOTSRlA==;EndpointSuffix=core.windows.net";


        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

//Create a unique name for the container
        String containerName = "cmdb2";

// Create the container and return a container client object
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        String localPath = path;
        String fileName = name;
        // File localFile = new File(localPath + fileName);

// Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

// Upload the blob
        BlobHttpHeaders hd = new BlobHttpHeaders();
         localPath = localPath.replace("\\","/");
        blobClient.uploadFromFile(localPath);
        hd.setContentType("Content-Type: image/jpeg");
        blobClient.setHttpHeaders(hd);

        String uri = blobClient.getBlobUrl();
        url = uri;
        System.out.println(uri);

    }



}
