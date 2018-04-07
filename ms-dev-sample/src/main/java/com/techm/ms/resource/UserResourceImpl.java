package com.techm.ms.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.techm.ms.model.Account;
import com.techm.ms.model.User;
import com.techm.ms.model.representation.ResourceCollection;
import com.techm.ms.service.AccountService;
import com.techm.ms.service.UserService;
import com.techm.ms.service.UserServiceImpl;

@Controller
public class UserResourceImpl implements UserResource {
	@Autowired
	UserServiceImpl userService; //Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/users";

	@Override
	public Response findAllUsers() {
		List<User> users = userService.findAllUsers();		
		if (users == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<User> user = new ResourceCollection<>(users);
		return Response.ok(user).links(link).build();
}
	@GET
	@Produces("application/Json")
	@Consumes("application/Json")
	@Path("/{id}")
	public User getUserById(@PathParam("id") long id) {
		return userService.getUserById(id);
	}
	@POST
	@Produces("application/Json")
	@Consumes("application/Json")
	public Response createUser(User user) {
		userService.createUser(user);
		 return Response.created(URI.create("/"+ user.getId())).build();
	}
	

}
