package ru.rsatu.rwa.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.service.UsersService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @PermitAll
    public List<UserDto> getUsers() {
        return usersService.getUsers();
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
    //todo permission check
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    @RolesAllowed({"ADMIN"})
    public UserDto saveUser(UserDto userDto) {
        return usersService.saveUser(userDto);
    }

    /**
     * Удаление пользователя
     */
    //todo permission check
    @DELETE
    @Path("/user/{user_id}")
    @RolesAllowed({"ADMIN"})
    public void deleteUser(@PathParam("user_id") Long userId) {
        usersService.deleteUser(userId);
    }

}
