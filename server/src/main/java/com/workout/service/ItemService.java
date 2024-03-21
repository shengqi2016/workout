package com.workout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.workout.model.Employee;
import com.workout.model.Item;
import com.workout.model.User;
import com.workout.model.Member;
import com.workout.model.Profile;
import com.workout.model.Record;
public interface ItemService {

	List<Item> getItems();

	User getUser(int id);
	
	List<User> getUsers();

	void AddUser(User user);

	void Record(Record record);

	int validation(Member member);
	
	Profile getProfile(int id);
	
}
