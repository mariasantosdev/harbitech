package br.com.harbitech.school.onboarding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OnboardingController {
    @GetMapping("/onboarding/study-mode")
    String dashboard() {
        return "onboarding/studyMode";
    }
}
