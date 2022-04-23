package collab.collabiz.service.login;

import collab.collabiz.controller.login.SessionConst;
import collab.collabiz.controller.login.dto.LoginReq;
import collab.collabiz.controller.login.dto.LoginRes;
import collab.collabiz.controller.login.dto.LogoutRes;
import collab.collabiz.entity.Member;
import collab.collabiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    /* 로그인 */
    public LoginRes login(LoginReq loginReq, HttpServletRequest request) {

        Member findMember = memberRepository.findByEmail(loginReq.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        // 이메일 패스워드 검증
        if (loginReq.getPassword().equals(findMember.getPassword())) {
            log.info("세션 등록 시작");
            HttpSession session = request.getSession();
            LoginSession loginSession = new LoginSession(findMember.getId(), findMember.getEmail());
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginSession);
            log.info("세션 등록 성공 = {}", loginSession.getEmail());
            return new LoginRes(findMember);
        } else {
            throw new IllegalArgumentException("아이디 비밀번호가 일치하지 않습니다.");
        }
    }

    /* 로그아웃 */
    public LogoutRes logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new IllegalArgumentException("로그인 정보가 없는 사용자입니다.");
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(email -> log.info("{} 사용자 로그아웃", session.getAttribute(email)));
        session.invalidate();
        return new LogoutRes("정상 로그아웃 처리되었습니다.");
    }
}
