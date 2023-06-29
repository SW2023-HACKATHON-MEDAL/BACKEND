package medal.backend.controller;

import lombok.AllArgsConstructor;
import medal.backend.Dto.*;
import medal.backend.entity.Alarm;
import medal.backend.entity.Member;
import medal.backend.service.MemberService;
import medal.backend.service.PillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class ApiController {

    private final PillService pillService;
    private final MemberService memberService;

    /**
     * 약 저장
     */
    @PostMapping("/save")
    public Long savePill(PillDto pillDto) {
        Long savedId = pillService.savePill(pillDto);
        return savedId;
    }

    /**
     * 약 정보 조회 //회원 아이디로 가져오는걸로 바꾸기
     */


    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity<?> saveMember(JoinFormDto joinFormDto) {
        Long memberId = memberService.saveMember(joinFormDto);
        return ResponseEntity.ok(true);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginMember(LoginFormDto loginFormDto, HttpSession session) {
        Member member = memberService.loginMember(loginFormDto);
        if(member == null) ResponseEntity.badRequest().build();

        //로그인 됐다면 세션에 저장
        session.setAttribute("loginMember", member.getId());
        return ResponseEntity.ok(true);
    }

    @PostMapping("/enroll-alarm")
    public ResponseEntity<?> enrollAlarm(AlarmFromDto alarmFromDto, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");

    }

    /**
     * 아침, 점심, 저녁 별 알약 정보
     */
    @GetMapping("/mainPage")
    public AlarmDto mainPage(HttpSession session) {
        return null;
    }

}

