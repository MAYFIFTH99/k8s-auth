package fastcampus.auth.repository;

import fastcampus.auth.model.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, Long> {

}
