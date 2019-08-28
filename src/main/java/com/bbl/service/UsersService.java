package com.bbl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bbl.pojo.Users;

public interface UsersService {
	
	void addUser(Users users);
	
	List<Users> findUserAll();
	
	Users findUserById(Integer id);
	
	void updateUser(Users users);
	
	void deleteUserById(Integer id);
	
	Page<Users> findUserByPage(Pageable pageable);
	
	void saveUsers(Users users);
}
