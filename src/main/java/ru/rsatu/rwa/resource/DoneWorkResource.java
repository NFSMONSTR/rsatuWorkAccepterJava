package ru.rsatu.rwa.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.dto.DoneWorkFullDto;
import ru.rsatu.rwa.pojo.dto.PageDto;
import ru.rsatu.rwa.service.DoneWorksService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Map;

@Path("/api/v1/")
public class DoneWorkResource {
    @Inject
    DoneWorksService doneWorksService;

    @Context
    SecurityContext context;

    @Inject
    JsonWebToken jwt;

    /**
     * Получение всех
     */
    @GET
    @Path("/donework")
    @PermitAll
    public PageDto<List<DoneWorkFullDto>> getDoneWorks(@DefaultValue("1") @QueryParam("page") Long page, @DefaultValue("10") @QueryParam("size") Long size, @QueryParam("rated") Boolean rated) {
        if (context.isUserInRole("ADMIN")) {
            Map.Entry<Long, List<DoneWorkFullDto>> doneWorks = doneWorksService.getDoneWorks(page, size, rated);
            return new PageDto<>(page, size, doneWorks.getKey(), doneWorks.getValue());
        } else if (context.isUserInRole("TEACHER")) {
            Long userId = Long.parseLong(jwt.getClaim("user_id").toString());
            Map.Entry<Long, List<DoneWorkFullDto>> doneWorks = doneWorksService.getDoneWorksByTeacher(userId,page,size,rated);
            return new PageDto<>(page, size, doneWorks.getKey(), doneWorks.getValue());
        } else {
            Long userId = Long.parseLong(jwt.getClaim("user_id").toString());
            Map.Entry<Long, List<DoneWorkFullDto>> doneWorks = doneWorksService.getDoneWorksByUser(userId,page,size,rated);
            return new PageDto<>(page, size, doneWorks.getKey(), doneWorks.getValue());
        }
    }

    /**
     * Получение
     */
    @GET
    @Path("/donework/{donework_id}")
    @PermitAll
    public DoneWorkFullDto getDoneWork(@PathParam("donework_id") Long id) {
        return doneWorksService.getDoneWork(id);
    }

    /**
     * Создание работы
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/donework")
    @PermitAll
    public DoneWorkDto saveWork(DoneWorkDto doneWorkDto) {
        doneWorkDto.setAuthorId(Long.parseLong(jwt.getClaim("user_id").toString()));
        return doneWorksService.saveDoneWork(doneWorkDto);
    }

//    /**
//     * Удаление работы
//     */
//    @DELETE
//    @Path("/work/{work_id}")
//    @RolesAllowed({"ADMIN", "TEACHER"})
//    public Response deleteWork(@PathParam("work_id") Long workId) {
//        if (context.isUserInRole("ADMIN") || context.getUserPrincipal().getName().equals(worksService.getWorkAuthorUsername(workId))) {
//            worksService.deleteWork(workId);
//            return Response.status(Response.Status.NO_CONTENT).build();
//        } else {
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//    }

}
