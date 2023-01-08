package ru.rsatu.rwa.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.CommentWorkDto;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.dto.PageDto;
import ru.rsatu.rwa.service.CommentWorksService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/api/v1/")
public class CommentWorkResource {
    @Inject
    CommentWorksService commentWorksService;

    @Context
    SecurityContext context;

    @Inject
    JsonWebToken jwt;

    /**
     * Получение всех
     */
    @GET
    @Path("/comment")
    @PermitAll
    public List<CommentWorkDto> getComments() {
        return commentWorksService.getCommentWorks();
    }

    /**
     * Получение
     */
    @GET
    @Path("/comment/{comment_id}")
    @PermitAll
    public CommentWorkDto getComment(@PathParam("comment_id") Long id) {
        return commentWorksService.getCommentWork(id);
    }

    /**
     * Создание
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/comment")
    @PermitAll
    public Response saveComment(CommentWorkDto commentWorkDto) {
        //todo check permissions
        commentWorksService.saveCommentWork(commentWorkDto);
        return Response.status(Response.Status.CREATED).build();
    }
}
