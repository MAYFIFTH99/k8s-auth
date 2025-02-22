package fastcampus.auth.util;

import static org.junit.jupiter.api.Assertions.*;

import fastcampus.auth.model.Employee;
import fastcampus.auth.model.Role;
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


        String token = JwtUtil.createToken(employee);

        assertEquals(JwtUtil.parseToken(token).get("kakaoNickName"), nickName);

    }

    @Test
    void test_role(){
        Role role1 = Role.builder()
                .id(1L)
                .name("role1")
                .build();

        Role role2 = Role.builder()
                .id(2L)
                .name("role2")
                .build();

        List<Role> roles = Arrays.asList(role1, role2);
        Set<Role> roleSet = new HashSet<>(roles);

        Employee employee = Employee.createEmployee(
                "first",
                "last",
                1L,
                "testNickName",
                roleSet
        );

        String token = JwtUtil.createToken(employee);
        List res = JwtUtil.parseToken(token).get("roles", List.class);
        assertEquals(roleSet.size(), res.size());
        assertTrue(res.contains(role1.getName()));
        assertTrue(res.contains(role2.getName()));
    }
}