package ru.rsatu.rwa.resource;


import ru.rsatu.rwa.pojo.dto.GroupDto;
import ru.rsatu.rwa.service.GroupsService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/")
public class GroupResource {
    @Inject
    GroupsService groupsService;

    /**
     * Получение всех групп
     */
    //todo remove
    @GET
    @Path("/group")
    @PermitAll
    public List<GroupDto> getGroups() {
        return groupsService.getGroups();
    }

    /**
     * Получение группы
     */
    @GET
    @Path("/group/{group_id}")
    @PermitAll
    public GroupDto getGroup(@PathParam("group_id") Long groupId) {
        return groupsService.getGroup(groupId);
    }

    /**
     * Создание группы
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/group")
    @RolesAllowed({"ADMIN"})
    public Response saveUser(GroupDto groupDto) {
        groupsService.saveGroup(groupDto);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Удаление группы
     */
    @DELETE
    @Path("/group/{group_id}")
    @RolesAllowed({"ADMIN"})
    public Response deleteGroup(@PathParam("group_id") Long groupId) {
        groupsService.deleteGroup(groupId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
