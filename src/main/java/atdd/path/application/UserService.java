package atdd.path.application;

import atdd.path.dao.MemberDao;
import atdd.path.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

	private MemberDao memberDao;

	public UserService(final MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional(readOnly = true)
	public Member findMemberByEmail(String email) {
		Optional<Member> memberOptional = memberDao.findByEmail(email);
		return memberOptional.orElse(null);
	}
}
