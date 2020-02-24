package atdd.path.application;

import atdd.path.dao.MemberDao;
import atdd.path.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static atdd.path.TestConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = UserService.class)
class UserServiceTest {

	private UserService userService;

	@MockBean
	private MemberDao memberDao;

	@BeforeEach
	void setUp() {
		userService = new UserService(memberDao);
	}

	@Test
	void findMemberByEmail() {
		// given
		Member member = Member.of(MEMBER_EMAIL, MEMBER_NAME, MEMBER_PASSWORD);
		given(memberDao.findByEmail(MEMBER_EMAIL))
			.willReturn(Optional.of(member));

		// when
		Member memberByEmail = userService.findMemberByEmail(MEMBER_EMAIL);

		// then
		assertThat(memberByEmail.getEmail()).isEqualTo(member.getEmail());
		assertThat(memberByEmail.getName()).isEqualTo(member.getName());
	}
}