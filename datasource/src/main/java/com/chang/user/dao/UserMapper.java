package com.chang.user.dao;

import com.chang.user.pojo.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 9:49 2022/11/8
 * @Modified By:
 */
@Mapper
@Repository
public interface UserMapper {

    UserBean selectById(int id);

    List<UserBean> selectList();
}
