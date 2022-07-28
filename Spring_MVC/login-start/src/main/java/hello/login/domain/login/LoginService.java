package hello.login.domain.login;


import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {


        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
        return byLoginId.filter(m -> m.getPassword().equals(password))
                .orElse(null);

    }
}
