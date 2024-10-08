package br.com.harbitech.school.userSelfAssesment;

import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import br.com.harbitech.school.user.CurrentUser;
import br.com.harbitech.school.user.User;
import br.com.harbitech.school.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequiredArgsConstructor
public class UserSelfAssessmentController {
    private final SubcategoryRepository subcategoryRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final UserSelfAssessmentRepository userSelfAssessmentRepository;

    @PostMapping("/user-self-assessment")
    public String userSelfAssessment(@RequestBody UserSelfAssessmentRequest request) {
        Subcategory subcategory = subcategoryRepository.findById(request.getSubcategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Subcategory not found"));

        String userName = currentUser.getCurrentUsername().stream().findFirst().orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "User not found"));

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Optional<UserSelfAssessment> existingAssessment = userSelfAssessmentRepository.findByUserAndCategory(user.getId(), subcategory.getCategory().getId());

        if (existingAssessment.isPresent()) {
            UserSelfAssessment assessmentToUpdate = existingAssessment.get();
            assessmentToUpdate.setSubcategory(subcategory);
            userSelfAssessmentRepository.save(assessmentToUpdate);
            return "redirect:/" + subcategory.getCategoryCodeUrl() + "/courses-by-levels?subcategoryId=" + subcategory.getId();
        } else {
            userSelfAssessmentRepository.save(new UserSelfAssessment(user, subcategory));
            return "redirect:/" + subcategory.getCategoryCodeUrl() + "/courses-by-levels?subcategoryId=" + subcategory.getId();
        }
    }
}
