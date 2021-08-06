package dev.hyperlisk;

import dev.hyperlisk.models.Picture;
import dev.hyperlisk.services.DatabaseService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/minipic")
public class MinipicResource {

    @Inject
    DatabaseService dbService;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance picture(byte[] image);
        public static native TemplateInstance upload();
    }

    @GET
    @Path("/upload/img")
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance getUpload() {
        return Templates.upload();
    }

    @GET
    @Path("/{title}")
    @Produces(MediaType.TEXT_HTML)
    public byte[] get(@PathParam("title") String imageTitle) {

        return dbService.getImage(imageTitle);

    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response post(@MultipartForm Picture request) {
        return dbService.add(request);
    }


}
