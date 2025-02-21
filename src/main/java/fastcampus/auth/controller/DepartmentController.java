package fastcampus.auth.controller;

import fastcampus.auth.model.Department;
import fastcampus.auth.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Basics", description = "기본 관리 API")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(description = "전사 부서 조회")
    @GetMapping(value = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> listDepartments() {
        return ResponseEntity.ok().body(departmentService.listDepartments());
    }
}
