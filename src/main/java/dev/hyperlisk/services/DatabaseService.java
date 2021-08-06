package dev.hyperlisk.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import dev.hyperlisk.models.Picture;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.InputStream;


@ApplicationScoped
public class DatabaseService {

    @Inject
    MongoClient mongoClient;

    private GridFSBucket fsBucket;

    public Response add(Picture picture) {
        try {
            InputStream inputStream = picture.imageData;

            GridFSUploadOptions options = new GridFSUploadOptions()
                    .chunkSizeBytes(358400)
                    .metadata(new Document("type", "presentation"));

            ObjectId fileId = getFileBucket().uploadFromStream(picture.imageTitle, inputStream, options);

        } catch (Exception e) {

        }

        return Response.ok().build();
    }

    public byte[] getImage(String imageTitle) {

        GridFSDownloadStream downloadStream = getFileBucket().openDownloadStream(imageTitle);
        int fileLength = (int) downloadStream.getGridFSFile().getLength();
        byte[] bytesToWriteTo = new byte[fileLength];
        downloadStream.read(bytesToWriteTo);
        downloadStream.close();

        return bytesToWriteTo;

    }


    public GridFSBucket getFileBucket() {
        if(fsBucket == null) {
            fsBucket = GridFSBuckets.create(mongoClient.getDatabase("minipic"));
        }

        return fsBucket;
    }

}
