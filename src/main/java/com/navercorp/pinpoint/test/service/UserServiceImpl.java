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
    public User get() {
        return this.userMapper.selectUser();
    }

}
