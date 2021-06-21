package com.yisquare.springboot.dao;

import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserDao {

    List<User> listUsers();

    List<User> listUsersByUserCode(QueryCondition userQuery);

    User getUserInfo(String userCode);

    int deleteUserByCode(String userCode);

    int addUser(User user);

    int updateUser(User user);

    int updateUserByPatch(User user);

    int getSystemCodeByUserCode(String userCode);

}
