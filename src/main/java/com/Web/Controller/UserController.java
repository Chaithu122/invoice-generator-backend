package com.Web.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Web.Entity.User;
import com.Web.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService=userService;
	}
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User createOrUpdateUser(@RequestBody User user, Authentication authentication) {
		try {
			if(!authentication.getName().equals(user.getClerkId())) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User does not have permission to access this resource");

			}
			return userService.saveOrUpdateUser(user);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}

	}

	
}
