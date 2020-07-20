package cn.jiyun.service;

import java.util.List;

import cn.jiyun.pojo.User;

public interface UserService {

	List<User> findUsernameAndPassword(User user);

	List<User> findUsername(String username);

	void add(User user);

}
