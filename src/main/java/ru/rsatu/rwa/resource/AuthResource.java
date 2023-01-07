package ru.rsatu.rwa.resource;

import io.quarkus.security.identity.request.AuthenticationRequest;
import ru.rsatu.rwa.pojo.dto.TokenDto;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.service.UsersService;
import ru.rsatu.rwa.utils.TokenUtils;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/api/v1/")
public class AuthResource {

    @Inject
    UsersService usersService;
    @Context
    SecurityContext context;
    @GET
    @Path("/auth")
    @PermitAll
    public TokenDto getToken() throws Exception {
        TokenDto token =  new TokenDto();
        User user = usersService.getUserByName(context.getUserPrincipal().getName());
        token.setToken(TokenUtils.generateToken(user.getUsername(), user.getId(), user.getRole(), (long) (24*60*60)));
        return token;
    }
}
