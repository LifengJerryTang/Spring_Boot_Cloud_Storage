package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO Users(username, salt, password, firstname, lastname) VALUES (#{username}," +
            " #{salt}, #{password}, #{firstname}, #{lastName})")
    public int insertUser(User user);

    @Select("SELECT * FROM Users WHERE username = #{username}")
    public User getUser(String username);


}
