package fastcampus.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class KakaoLoginUIController {

    @Value("${kakao.client_id}")
    private String CLIENT_ID;

    @Value("${kakao.redirect_uri}")
    private String REDIRECT_URI;

    @GetMapping("/page")
    public String loginPage(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI;

        model.addAttribute("location", location);
        return "login";
    }
}
