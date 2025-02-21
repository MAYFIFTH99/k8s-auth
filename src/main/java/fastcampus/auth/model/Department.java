package fastcampus.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Department {

    @Schema(example = "123", description = "auto increment pk")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "인사팀", description = "부서 이름")
    private String deptName;

    @Schema(example = "123456", description = "부서장 ID")
    @OneToOne
    @JoinColumn(name = "team_lead_id", referencedColumnName = "id")
    private Employee teamLeader;

}
