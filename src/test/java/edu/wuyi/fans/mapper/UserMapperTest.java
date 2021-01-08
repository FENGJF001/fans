package edu.wuyi.fans.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void getUserByEmailIgnoreStatusTest(){
        System.out.println(userMapper.getUserByEmailIgnoreStatus("111111@qq.com"));
    }

    @Test
    void getUserByTelIgnoreStatusTest(){
        System.out.println(userMapper.getUserByTelIgnoreStatus("1111111")==null);
    }

}