package ru.rsatu.rwa.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.AvatarDto;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Path("/")
public class FileResource {

    @GET
    @PermitAll
    @Path("/avatar/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getAvatar(@PathParam("filename") String filename) throws FileNotFoundException {
        InputStream is;
        try {
            is = new FileInputStream("static/avatar/"+filename);
        } catch (FileNotFoundException e) {
            is = new FileInputStream("static/avatar/fallback/noavatar.png");
        }

        return Response.status(Response.Status.OK).entity(is).build();
    }

    @GET
    @PermitAll
    @Path("/static/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("filename") String filename) throws FileNotFoundException {
        InputStream is = new FileInputStream("static/"+filename);
        return Response.status(Response.Status.OK).entity(is).build();
    }

}
