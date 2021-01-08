package edu.wuyi.fans.service.impl;

import edu.wuyi.fans.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PermissionServiceImplTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    void listPermsByRoleId() {
        permissionService.listPermsByRoleId(3);
    }
}