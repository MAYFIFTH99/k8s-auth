package fastcampus.auth.repository;

import fastcampus.auth.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Boolean existsByKakaoNickName(String kakaoNickName);

    Employee findByKakaoNickName(String kakaoNickName);
}
