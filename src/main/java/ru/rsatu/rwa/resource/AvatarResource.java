package ru.rsatu.rwa.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.AvatarDto;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Path("/api/v1")
public class AvatarResource {
    @Inject
    JsonWebToken jwt;


    @POST
    @PermitAll
    @Path("/avatar")
    public Response saveAvatar(AvatarDto avatarDto) {
        byte[] data = DatatypeConverter.parseBase64Binary(avatarDto.getFile().substring(avatarDto.getFile().indexOf(",") + 1));
        String fname = "static/avatar/"+jwt.getClaim("user_id").toString()+ ".png";
        try (FileOutputStream fos = new FileOutputStream(fname)) {
            fos.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @PermitAll
    @Path("/avatar")
    public Response deleteAvatar() {
        String fname = "static/avatar/"+jwt.getClaim("user_id").toString()+ ".png";
        File file = new File(fname);
        file.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
