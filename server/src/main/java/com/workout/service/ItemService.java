package com.workout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.workout.model.Employee;
import com.workout.model.Item;
import com.workout.model.User;
public interface ItemService {

	List<Item> getItems();

	Employee getEmployee(long id);

	User getUser(int id);
	
	List<User> getUsers();

	void AddUser(User user);
}
