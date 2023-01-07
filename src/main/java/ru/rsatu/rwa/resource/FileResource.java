package ru.rsatu.rwa.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/")
public class FileResource {
    @Inject
    JsonWebToken jwt;

    @GET
    @PermitAll
    @Path("/avatar/{filename}")
    public Response getAvatar(@PathParam("filename") String filename) throws FileNotFoundException {
        //todo
        InputStream is = new FileInputStream("static/avatar/"+filename);
        return Response.status(Response.Status.OK).entity(is).build();
    }

    @GET
    @PermitAll
    @Path("/static/{filename}")
    public Response getFile(@PathParam("filename") String filename) throws FileNotFoundException {
        InputStream is = new FileInputStream("static/"+filename);
        return Response.status(Response.Status.OK).entity(is).build();
    }

}
