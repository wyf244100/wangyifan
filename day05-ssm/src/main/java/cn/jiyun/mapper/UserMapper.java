package cn.jiyun.mapper;

import java.util.List;

import cn.jiyun.pojo.User;

public interface UserMapper {

	List<User> findUsernameAndPassword(User user);

	List<User> findUsername(String username);

	void add(User user);

}
