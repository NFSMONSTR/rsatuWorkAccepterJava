package ru.rsatu.rwa.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.service.WorksService;

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
public class WorkResource {
    @Inject
    WorksService worksService;

    @Context
    SecurityContext context;

    @Inject
    JsonWebToken jwt;

    /**
     * Получение всех работ
     */
    @GET
    @Path("/work")
    @PermitAll
    public List<WorkDto> getWorks(@DefaultValue("1") @QueryParam("page") Long page, @DefaultValue("10") @QueryParam("size") Long size) {
        return worksService.getWorks(page, size);
    }

    /**
     * Получение работы
     */
    @GET
    @Path("/work/{work_id}")
    @PermitAll
    public WorkDto getWork(@PathParam("work_id") Long workId) {
        return worksService.getWork(workId);
    }

    /**
     * Создание работы
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/work")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public Response saveWork(WorkDto workDto) {
        if (workDto.getAuthor() == null) {
            workDto.setAuthor(Long.parseLong(jwt.getClaim("user_id").toString()));
        }
        worksService.saveWork(workDto);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Удаление работы
     */
    @DELETE
    @Path("/work/{work_id}")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public Response deleteWork(@PathParam("work_id") Long workId) {
        if (context.isUserInRole("ADMIN") || context.getUserPrincipal().getName().equals(worksService.getWorkAuthorUsername(workId))) {
            worksService.deleteWork(workId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
