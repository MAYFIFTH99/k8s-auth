package fastcampus.auth.repository;

import fastcampus.auth.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByAppIdAndApiId(Long appId, Long apiId);
}
