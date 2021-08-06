package dev.hyperlisk.models;


import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Objects;

public class Picture {

    @FormParam("imageData")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream imageData;

    @FormParam("imageTitle")
    @PartType(MediaType.TEXT_PLAIN)
    public String imageTitle;




    public Picture(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public Picture() {}


    @Override
    public String toString() {
        return "Picture{" +
                "imageData=" + imageData +
                ", imageTitle='" + imageTitle + '\'' +
                '}';
    }
}
