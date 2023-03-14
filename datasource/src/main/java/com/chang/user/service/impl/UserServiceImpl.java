package com.chang.user.service.impl;

import com.chang.user.dao.UserMapper;
import com.chang.user.pojo.UserBean;
import com.chang.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 15:53 2022/11/10
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(value = "db2TransactionManager")
    @Override
    public UserBean selectUserById() {
        return userMapper.selectById(2);
    }
}
