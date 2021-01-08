package edu.wuyi.fans.mapper;

import edu.wuyi.fans.model.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PermissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    void baseTest(){
        Permission permission = new Permission();
        permission.setName("发布图片");
        permission.setUrl("/picture/post");
        permission.setSummary("发布图片");
        permission.setPriority(30);
        permissionMapper.insert(permission);
    }

}