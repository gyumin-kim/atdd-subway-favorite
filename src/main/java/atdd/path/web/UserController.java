package atdd.path.web;

import atdd.path.application.dto.UserResponseView;
import atdd.path.domain.Member;
import atdd.path.user.security.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	public UserController() {
	}

	@GetMapping("/users/me")
	public ResponseEntity retrieveMyInfo(@LoginUser Member member) {
		UserResponseView userResponseView = UserResponseView.of(member);
		return ResponseEntity.ok(userResponseView);
	}
}
