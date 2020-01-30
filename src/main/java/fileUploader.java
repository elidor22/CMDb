import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import java.io.IOException;


public class fileUploader {

    public static void main(String args[]) throws IOException {
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=cmdbcit;AccountKey=4CMZ33uOXJJDOTh+F2qWrp+0xsdhuUH/UWh5AJmLXO6116wlOtmILtGVuDWtwl8V2FLtkKGWmX/rlVcKOTSRlA==;EndpointSuffix=core.windows.net";


        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

//Create a unique name for the container
        String containerName = "cmdb2";

// Create the container and return a container client object
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        String localPath = "/home/elidor/Downloads/the_punisher.jpg";
        String fileName = "the_punisher.jpg";
       // File localFile = new File(localPath + fileName);

// Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

// Upload the blob
        BlobHttpHeaders hd = new BlobHttpHeaders();
        blobClient.uploadFromFile(localPath);
        hd.setContentType("Content-Type: image/jpeg");
        blobClient.setHttpHeaders(hd);

        String uri = blobClient.getBlobUrl();
        System.out.println(uri);
    }

}
