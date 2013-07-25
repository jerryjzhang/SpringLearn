package com.junz.hibernate.dao;

import com.junz.hibernate.domain.User;

public interface UserDao {
	public User get(int id);
	public void save(User user);
}
