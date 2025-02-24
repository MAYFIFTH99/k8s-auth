package fastcampus.auth.util;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.auth.model.Employee;
import fastcampus.auth.model.EmployeeRole;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class JwtUtilTest {

    @Test
    public void test_nickname(){
        String nickName = "testNickName";
        Employee employee = Employee.createEmployee(
                "first",
                "last",
                1L,
                nickName,
                null
        );


        String token = JwtUtil.createUserToken(employee);

        assertEquals(JwtUtil.parseToken(token).get("kakaoNickName"), nickName);

    }

    @Test
    void test_role(){
        EmployeeRole employeeRole1 = EmployeeRole.builder()
                .id(1L)
                .name("role1")
                .build();

        EmployeeRole employeeRole2 = EmployeeRole.builder()
                .id(2L)
                .name("role2")
                .build();

        List<EmployeeRole> employeeRoles = Arrays.asList(employeeRole1, employeeRole2);
        Set<EmployeeRole> employeeRoleSet = new HashSet<>(employeeRoles);

        Employee employee = Employee.createEmployee(
                "first",
                "last",
                1L,
                "testNickName",
                employeeRoleSet
        );

        String token = JwtUtil.createUserToken(employee);
        List res = JwtUtil.parseToken(token).get("roles", List.class);
        assertEquals(employeeRoleSet.size(), res.size());
        assertTrue(res.contains(employeeRole1.getName()));
        assertTrue(res.contains(employeeRole2.getName()));
    }
}