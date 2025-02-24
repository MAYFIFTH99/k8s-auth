package fastcampus.auth.service;

import fastcampus.auth.model.Employee;
import fastcampus.auth.repository.EmployeeRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> listEmployees() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("name: {}", name);
        return employeeRepository.findAll();
    }

    public Employee createEmployee(String firstName, String lastName, Long departmentId, String kakaoNickName) {

        if (employeeRepository.existsByKakaoNickName(kakaoNickName)) {
            throw new DuplicateKeyException("이미 존재하는 닉네임입니다.");
        }

        Employee employee = Employee.createEmployee(firstName, lastName, departmentId, kakaoNickName,null);
        return employeeRepository.save(employee);
    }

    // @Cacheable -> 없으면 캐시에 적재, 없으면 TTL update
    @Cacheable(cacheNames = "employee", key = "#id")
    public Employee findEmployeeById(Long id) {
        log.info("Cache miss for employee with id {}", id);  // 캐시 미스일 때 로그 출력
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id를 찾을 수 없습니다."));
    }
}
