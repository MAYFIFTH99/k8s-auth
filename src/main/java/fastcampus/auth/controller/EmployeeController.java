package fastcampus.auth.controller;

import fastcampus.auth.model.Employee;
import fastcampus.auth.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Basics", description = "기본 관리 API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listALl() {
        return ResponseEntity.ok().body(employeeService.listEmployees());
    }

    @PostMapping(value = "/admin/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@RequestParam String firstName,
            @RequestParam String lastName, @RequestParam Long departmentId
    , @RequestParam String kakaoNickName) {

        Employee newEmployee = employeeService.createEmployee(firstName, lastName, departmentId, kakaoNickName);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    /**
     * Cache를 이용한 단건 조회 테스트를 위한 테스트 API
     */
    @GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.findEmployeeById(id));
    }

}
