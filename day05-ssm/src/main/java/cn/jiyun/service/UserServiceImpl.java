package cn.jiyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiyun.mapper.UserMapper;
import cn.jiyun.pojo.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	

	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> findUsernameAndPassword(User user) {
		return userMapper.findUsernameAndPassword(user);
	}

	@Override
	public List<User> findUsername(String username) {
		return userMapper.findUsername(username);
	}

	@Override
	public void add(User user) {
		userMapper.add(user);
	}
	
}
