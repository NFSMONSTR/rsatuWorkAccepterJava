package ru.rsatu.rwa.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.PageDto;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.service.UsersService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/")
public class UserResource {

    @Inject
    JsonWebToken jwt;
    @Inject
    UsersService usersService;

    /**
     * Получение всех пользователей
     */
    //todo remove
    @GET
    @Path("/user")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public PageDto<List<UserDto>> getUsers(@DefaultValue("1") @QueryParam("page") Long page, @DefaultValue("10") @QueryParam("size") Long size) {
        return new PageDto<>(page,size,usersService.getCount(size),usersService.getUsers(page, size));
    }

    /**
     * Получение пользователя
     */
    @GET
    @Path("/user/{user_id}")
    @PermitAll
    public UserDto getUser(@PathParam("user_id") Long userId) {
        return usersService.getUser(userId);
    }

    /**
     * Создание пользователя
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    @RolesAllowed({"ADMIN"})
    public UserDto saveUser(UserDto userDto) {
        return usersService.saveUser(userDto);
    }

    @POST
    @Path("/user/{user_id}/group/{group_id}")
    @RolesAllowed({"ADMIN"})
    public Response addUserToGroup(@PathParam("user_id") Long userId, @PathParam("group_id") Long groupId) {
        usersService.addToGroup(userId,groupId);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Удаление пользователя
     */
    @DELETE
    @Path("/user/{user_id}")
    @RolesAllowed({"ADMIN"})
    public void deleteUser(@PathParam("user_id") Long userId) {
        usersService.deleteUser(userId);
    }

}
