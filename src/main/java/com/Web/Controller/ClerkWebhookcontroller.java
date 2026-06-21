package com.Web.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Web.Entity.User;
import com.Web.Service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/webhooks")
public class ClerkWebhookcontroller {
	 @Value("${clerk.webhook.secret}")
	    private String webhookSecret;

	    private final UserService userService;
	    
	    public ClerkWebhookcontroller(UserService userService ) {
	    	this.userService=userService;
	    }

	    @PostMapping("/clerk")
	    public ResponseEntity<?> handleClerkWebhook(@RequestHeader("svix-id") String svixId,
	            @RequestHeader("svix-timestamp") String svixTimestamp,
	            @RequestHeader("svix-signature") String svixSignature,
	            @RequestBody String payload) {
	        try {
	            verifyWebhookSignature(svixId, svixTimestamp, svixSignature, payload);

	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode rootNode = objectMapper.readTree(payload);

	            String eventType = rootNode.path("type").asText();

	            switch (eventType) {
	                case "user.created":
	                    handleUserCreated(rootNode.path("data"));
	                    break;
	                case "user.updated":
	                    handleUserUpdated(rootNode.path("data"));
	                    break;
	                case "user.deleted":
	                    handleUserDeleted(rootNode.path("data"));
	                    break;
	            }
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
	        }

	    }

	    private void handleUserDeleted(JsonNode data) {
	        String clerkId = data.path("id").asText();
	        userService.deleteAccount(clerkId);
	    }

	    private void handleUserUpdated(JsonNode data) {
	        String clerkId = data.path("id").asText();
	        User existingUser = userService.getAccountByClerkId(clerkId);

	        existingUser.setEmail(data.path("email_addresses").path(0).path("email_address").asText());
	        existingUser.setFirstName(data.path("first_name").asText());
	        existingUser.setLastName(data.path("last_name").asText());
	        existingUser.setPhotoUrl(data.path("image_url").asText());

	        userService.saveOrUpdateUser(existingUser);
	    }

	  
	    private void handleUserCreated(JsonNode data) {

	        User newUser = new User();

	        newUser.setClerkId(data.path("id").asText());
	        newUser.setEmail(
	            data.path("email_addresses")
	                .path(0)
	                .path("email_address")
	                .asText()
	        );
	        newUser.setFirstName(data.path("first_name").asText());
	        newUser.setLastName(data.path("last_name").asText());
	        newUser.setPhotoUrl(data.path("image_url").asText());

	        userService.saveOrUpdateUser(newUser);
	    }

	    private boolean verifyWebhookSignature(String svixId, String svixTimestamp, String svixSignature, String payload) {
	        return true;
	    }

}
