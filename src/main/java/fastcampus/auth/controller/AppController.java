package fastcampus.auth.controller;


import fastcampus.auth.model.App;
import fastcampus.auth.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Basics", description = "앱 관련 API")
public class AppController {

    private final AppService appService;


    /**
     * 전체 시스템 조회 API
     */
    @Operation(description = "시스템 전체 조회")
    @GetMapping(value = "/apps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<App>> listAll(){
        return new ResponseEntity<>(appService.listApps(), HttpStatus.OK);
    }

}
