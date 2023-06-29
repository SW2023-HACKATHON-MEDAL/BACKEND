package medal.backend.controller;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.JoinFormDto;
import medal.backend.Dto.LoginFormDto;
import medal.backend.Dto.ManageInfoDto;
import medal.backend.Dto.PillDto;
import medal.backend.entity.Member;
import medal.backend.service.AlarmService;
import medal.backend.service.MemberService;
import medal.backend.service.PillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final PillService pillService;
    private final MemberService memberService;
    private final AlarmService alarmService;

    /**
     * 약 저장
     */
    @PostMapping("/save")
    public Long savePill(PillDto pillDto) {
        Long savedId = pillService.savePill(pillDto);
        return savedId;
    }

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
        return ResponseEntity.ok(member.getRole());
    }

    /**
     * 관리하는 회원의 약 복용 상태
     */
    @GetMapping("/manage-info")
    public ManageInfoDto manageInfo(HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");
        ManageInfoDto manageInfoDto = memberService.findManagedMemberInfo(memberId);
        return manageInfoDto;
    }
}
