package fastcampus.auth.service;

import fastcampus.auth.model.Department;
import fastcampus.auth.repository.DepartmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> listDepartments() {
        return departmentRepository.findAll();
    }

}
