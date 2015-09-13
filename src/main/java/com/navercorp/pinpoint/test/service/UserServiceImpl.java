package com.navercorp.pinpoint.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navercorp.pinpoint.test.dao.UserMapper;
import com.navercorp.pinpoint.test.model.User;

/**
 * @author hyungil.jeong
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(String id) {
        this.userMapper.selectUser(id);
        return User.MOCK_USER;
    }

    @Override
    public void insert(String id, String nickName) {
        this.userMapper.insertUser(User.MOCK_USER);
    }

    @Override
    public void delete(String id) {
        this.userMapper.deleteUser(id);
    }

    @Override
    public void update(String id, String nickName) {
        this.userMapper.updateUser(User.MOCK_USER);
    }

}
