package fastcampus.auth.repository;

import fastcampus.auth.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
    Api findByMethodAndPath(String method, String path);

}
