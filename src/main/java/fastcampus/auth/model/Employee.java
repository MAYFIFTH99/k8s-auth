package fastcampus.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    public static Employee createEmployee(String firstName, String lastName, Long departmentId,
            String kakaoNickName, Set<EmployeeRole> employeeRoles) {


        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .departmentId(departmentId)
                .kakaoNickName(kakaoNickName)
                .employeeRoles(employeeRoles)
                .build();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Long departmentId;

    private String kakaoNickName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_role_mapping",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<EmployeeRole> employeeRoles = new HashSet<>();

    public static boolean isHR(Employee employee){
        return employee.getEmployeeRoles().stream().anyMatch(employeeRole -> employeeRole.getName().equals("인사팀"));
    }


}
