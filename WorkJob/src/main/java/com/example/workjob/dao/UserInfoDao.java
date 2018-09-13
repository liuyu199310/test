package com.example.workjob.dao;



import org.apache.ibatis.annotations.Mapper;


import com.example.workjob.entity.UserInfo;


@Mapper
public interface UserInfoDao {

	public UserInfo findByUsername(String username);

	public void insertUserInfo(UserInfo userInfo);

	public int updataPassword(UserInfo userInfo);

	public void updataUserInfo(UserInfo userInfo);

}
