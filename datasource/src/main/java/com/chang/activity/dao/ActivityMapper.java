package com.chang.activity.dao;

import com.chang.activity.pojo.ActivityBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 9:49 2022/11/8
 * @Modified By:
 */
@Mapper
@Repository
public interface ActivityMapper {

    ActivityBean selectById(int id);
}
