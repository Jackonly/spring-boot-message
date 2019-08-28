package com.bbl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbl.dao.UsersRepository;
import com.bbl.mapper.UsersMapper;
import com.bbl.pojo.Users;
import com.bbl.redis.config.RedisConfig;
import com.bbl.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	
	@Override
	//@CacheEvict(value="users",allEntries=true) 清除缓存中以users缓存策略缓存的对象
	@CacheEvict(value="users",allEntries=true)
	public void addUser(Users users) {
		this.usersMapper.insertUser(users);
	}
	@Override
	public List<Users> findUserAll() {
		System.out.println("redis:数据"+(String)this.redisTemplate.opsForValue().get("qq"));
		return this.usersMapper.selectUsersAll();
	}

	@Override
	//@Cacheable:对当前查询的对象做缓存处理
	@Cacheable(value="users")
	public Users findUserById(Integer id) {
		return this.usersMapper.selectUsersById(id);
	}

	@Override
	public void updateUser(Users users) {
		this.usersMapper.updateUser(users);
	}

	@Override
	public void deleteUserById(Integer id) {
		this.usersMapper.deleteUserById(id);
	}
	
	@Override
	@Cacheable(value="users",key="#pageable.pageSize")
	public Page<Users> findUserByPage(Pageable pageable) {
		return this.usersRepository.findAll(pageable);
	}

	@Override
	//@CacheEvict(value="users",allEntries=true) 清除缓存中以users缓存策略缓存的对象
	//@CacheEvict(value="users",allEntries=true)
	public void saveUsers(Users users) {
		this.usersRepository.save(users);
	}
		
	
}
