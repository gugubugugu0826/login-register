package com.billy.service;

import com.billy.mapper.UserMapper;
import com.billy.pojo.User;
import com.billy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /*
    * 登录方法
    * */
    public User login(String username,String password){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.select(username,password);
        //释放资源
        sqlSession.close();
        return user;
    }

    /*
     * 注册方法
     * */
    public boolean register(User user){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());

        if(u == null){
            //用户名不存在可以注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return  u == null;
    }
}
