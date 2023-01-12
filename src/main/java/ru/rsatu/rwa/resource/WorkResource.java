package ru.rsatu.rwa.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.PageDto;
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
import java.util.Map;

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
    public PageDto<List<WorkDto>> getWorks(@DefaultValue("1") @QueryParam("page") Long page, @DefaultValue("10") @QueryParam("size") Long size) {
        if (context.isUserInRole("ADMIN")) {
            return new PageDto<>(page, size, worksService.getCount(size), worksService.getWorks(page, size));
        } else if (context.isUserInRole("TEACHER")) {
            Map.Entry<Long,List<WorkDto>> works = worksService.getWorksTeacher(page,size,Long.parseLong(jwt.getClaim("user_id").toString()));
            return new PageDto<>(page, size, works.getKey(), works.getValue());
        } else {
            Map.Entry<Long,List<WorkDto>> works = worksService.getWorksUser(page,size,Long.parseLong(jwt.getClaim("user_id").toString()));
            return new PageDto<>(page, size, works.getKey(), works.getValue());
        }
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
    public WorkDto saveWork(WorkDto workDto) {
        if (workDto.getAuthor() == null) {
            workDto.setAuthor(Long.parseLong(jwt.getClaim("user_id").toString()));
        }
        return worksService.saveWork(workDto);
    }

    @POST
    @Path("/work/{work_id}/share/{group_id}")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public Response connectWork(@PathParam("work_id") Long work_id, @PathParam("group_id") Long groupId) {
        Long userId = Long.parseLong(jwt.getClaim("user_id").toString());
        if (worksService.connectWork(userId, work_id, groupId)) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/work/{work_id}/share/{group_id}")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public Response disconnectWork(@PathParam("work_id") Long work_id, @PathParam("group_id") Long groupId) {
        Long userId = Long.parseLong(jwt.getClaim("user_id").toString());
        if (worksService.disconnectWork(userId, work_id, groupId)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
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
