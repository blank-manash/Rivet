package com.demo.application.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.application.models.User;
import com.demo.application.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ApplicationController {

	private UserService userService;

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String welcome() {
		return "<html><head><title>Page Title</title></head><body><h1>Rivet Backend</h1><ul><li><code>/find-users<code> : Find with filters <li><code>/find-friends<code> : Request Param is currentUser <li><code>/search-by-tags<code> : Request Param is tags=1,2,3 <li><code>/register-user<code> : Request Body is User JSON Body <li><code>/update-user<code> : Request Body is User JSON Body <li><code>/add-tags<code> : Request Param is id_a=1 & id_b=2 <li><code>/block-user<code> Request Param id_a, id_b <li><code>/unblock-user<code> : Same as block user <li><code>/add-friend<code> : Same as block user <li><code>/remove-friend<code> : You got it, same- id_a, id_b <li><code>/add-tags<code> : Request Param is id=1 & tag=\"Music, Sports, Academics\"<ul></body></html>";
	}

	@GetMapping("/find-user")
	public List<User> getUsersWith(@RequestParam Map<String, String> params) {
		log.info("Got find-user Request in Contorller, Delegating to Service");
		return userService.getUsersWith(params);
	}

	@GetMapping("/find-friends")
	public List<User> getFriends(@RequestParam("currentUser") Long id) {
		log.info("Controller getFriends Request in Controller, Delegation to Service");
		return userService.getFriends(id);
	}

	@GetMapping("/search-by-tags")
	public List<User> searchByTags(@RequestParam List<String> tags) {
		return userService.searchByTag(tags);
	}

	@PostMapping("/register-user")
	public User registerUser(@RequestBody User user) {
		log.info("Registering User: {}", user.toString());
		return userService.registerUser(user);
	}

	@PutMapping("/update-user")
	public boolean updateUser(@RequestBody User user) {
		log.info("Updating User Information for User {}", user.toString());
		return userService.updateUser(user);
	}

	@PostMapping("/add-tags")
	public boolean addTags(@RequestParam("id") Long idA, @RequestParam("tag") String tag) {
		return userService.addTags(idA, tag);
	}

	@PostMapping("/block-user")
	public boolean blockUser(@RequestParam("id_a") Long idA, @RequestParam("id_b") Long idB) {
		return userService.blockUser(idA, idB);
	}

	@PostMapping("/add-friend")
	public boolean addFriends(@RequestParam("id_a") Long idA, @RequestParam("id_b") Long idB) {
		return userService.addFriend(idA, idB);
	}

	@DeleteMapping("/remove-friend")
	public boolean removeFriends(@RequestParam("id_a") Long idA, @RequestParam("id_b") Long idB) {
		return userService.removeFriend(idA, idB);
	}

	@DeleteMapping("/unblock-user")
	public boolean unblockUser(@RequestParam("id_a") Long idA, @RequestParam("id_b") Long idB) {
		return userService.unblockUser(idA, idB);
	}

	@DeleteMapping("/remove-tags")
	public boolean removeTags(@RequestParam("id") Long idA, @RequestParam("tag") String tag) {
		return userService.removeTags(idA, tag);
	}

};
