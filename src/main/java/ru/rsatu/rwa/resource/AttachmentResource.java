package ru.rsatu.rwa.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.AttachmentDto;
import ru.rsatu.rwa.service.AttachmentsService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Path("/api/v1/")
public class AttachmentResource {
    @Inject
    AttachmentsService attachmentsService;
    @Inject
    JsonWebToken jwt;
    @Context
    SecurityContext context;


    //todo remove
    @GET
    @Path("/attachment")
    @PermitAll
    public List<AttachmentDto> getAttachments() {
        return attachmentsService.getAttachments();
    }

    @GET
    @Path("/attachment/{attachment_id}")
    @PermitAll
    public AttachmentDto getAttachment(@PathParam("attachment_id") Long attachmentId) {
        return attachmentsService.getAttachment(attachmentId);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/attachment")
    @PermitAll
    public Response saveAttachment(AttachmentDto attachmentDto) {
        if (attachmentDto.getAuthor() == null) {
            attachmentDto.setAuthor(Long.parseLong(jwt.getClaim("user_id").toString()));
        }
        if (!attachmentDto.getIs_link()) {
            byte[] data = DatatypeConverter.parseBase64Binary(attachmentDto.getFile().substring(attachmentDto.getFile().indexOf(",") + 1));
            String fname = "static/"+ System.currentTimeMillis() +'_'+attachmentDto.getName();
            try (FileOutputStream fos = new FileOutputStream(fname)) {
                fos.write(data);
                attachmentDto.setLink("/"+fname);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        attachmentsService.saveAttachment(attachmentDto);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/attachment/{attachment_id}")
    @PermitAll
    public Response deleteAttachment(@PathParam("attachment_id") Long attachmentId) {
        AttachmentDto attachment = attachmentsService.getAttachment(attachmentId);
        if (context.isUserInRole("ADMIN") || Long.valueOf(jwt.getClaim("user_Id").toString()).equals(attachment.getAuthor())) {
            File file = new File("static/"+attachment.getName());
            file.delete();
            attachmentsService.deleteAttachment(attachmentId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
