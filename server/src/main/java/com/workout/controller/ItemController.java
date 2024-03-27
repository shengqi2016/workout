package com.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workout.model.IdDTO;
import com.workout.model.Item;
import com.workout.model.Member;
import com.workout.model.Profile;
import com.workout.model.Record;
import com.workout.model.User;
import com.workout.model.Username;
import com.workout.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@PostMapping("/add")
	public void NewRecord(@RequestBody User user) {
		System.out.println("Received user data: " + user.getEmail() + user.getUsername());
		itemService.AddUser(user);
	}

	@PostMapping("/record")
	public void ANewRecord(@RequestBody Record record) {
		System.out.println("Received record: " + record.getWeight());
		itemService.Record(record);
	}
	@PostMapping("/inf")
	public Profile getprofile(@RequestBody IdDTO idDto){
		Profile profile=new Profile();
			profile=itemService.getProfile(idDto.getId());
		return profile;
	}

	@RequestMapping("/items")
	public List<Item> getItems() {
		return itemService.getItems();
	}

	@PostMapping("/login")
	public int login(@RequestBody Member member){
		int id=0;
			System.out.println(member.getUsername()+"      "+member.getPassword());
			id= itemService.validation(member);
		 return id;
	}
	@PostMapping("/usernamecheck")
	public boolean usernameCheck(@RequestBody Username username){
		boolean pass=true;
		pass=itemService.usernameCheck(username.getUsername());
		 return pass;
	}

	@PostMapping("/signupaccount")
	public int signupaccount(@RequestBody Member member){
		System.out.println(member.getPassword()+member.getUsername()+member.isAdmin());
		int id=0;
		id=itemService.signupaccount(member);
		return id;

	}
	@RequestMapping("/user")
	public User getUser() {
		return itemService.getUser(2);
	}

	@RequestMapping("/users")
	public List<User> getUsers() {
		return itemService.getUsers();
	}
	@PostMapping("/addProfile")
	public void addProfile(@RequestBody Profile profile){
		System.out.println("addprofile"+profile.getHeight()+"|"+profile.getId()+"|"+profile.getDate());
		itemService.addprofile(profile);
	}

}
