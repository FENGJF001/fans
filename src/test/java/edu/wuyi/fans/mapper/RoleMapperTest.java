package edu.wuyi.fans.mapper;

import edu.wuyi.fans.model.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    void baseTest(){
        Role role = new Role();
        role.setType("user");
        role.setSummary("普通用户");
        roleMapper.insert(role);
    }

    @Test
    void getRoleByPermissionId() {
    }

    @Test
    void listRolesByUid() {
        List<Role> roles = roleMapper.listRolesByUid("2f9f79d929c347be9376840d59c7f953");
        roles.forEach(role -> {
            System.out.println(role);
        });
    }
}