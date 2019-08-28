package com.bbl.mapper;

import java.util.List;

import com.bbl.pojo.Users;

public interface UsersMapper {
	
	void insertUser(Users users);
	
	List<Users> selectUsersAll();
	
	Users selectUsersById(Integer id);
	
	void updateUser(Users users);
	
	void deleteUserById(Integer id);
}
