package fastcampus.auth.service;

import fastcampus.auth.model.App;
import fastcampus.auth.repository.AppRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public List<App> listApps(){
        return appRepository.findAll();
    }

}
