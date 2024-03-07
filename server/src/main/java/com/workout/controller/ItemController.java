package com.workout.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.workout.model.Employee;
import com.workout.model.Item;
import com.workout.model.User;
import com.workout.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@PostMapping("/add")
	public void AddUser(@RequestBody User user){
		  System.out.println("Received user data: " + user.getEmail()+user.getUsername());
		itemService.AddUser(user);
}

	
	@RequestMapping("/items")
	public List<Item> getItems() {
		return itemService.getItems();
	}

	@RequestMapping("/db")
	public Employee getData() {
		return itemService.getEmployee(1);
	}

	@RequestMapping("/user")
	public User getUser() {
		return itemService.getUser(2);
	}
	@RequestMapping("/users")
	public List<User> getUsers() {

		return itemService.getUsers();
	}


	



}
