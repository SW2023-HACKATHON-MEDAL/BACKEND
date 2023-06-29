package medal.backend.controller;

import lombok.AllArgsConstructor;
import medal.backend.Dto.JoinFormDto;
import medal.backend.Dto.LoginFormDto;
import medal.backend.Dto.MainDto;
import medal.backend.Dto.PillDto;
import medal.backend.service.MemberService;
import medal.backend.service.PillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("/find/{id}")
    public PillDto findPill(@PathVariable("id") Long id) {
        PillDto pillDto = pillService.findPillById(id);
        return pillDto;
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
    public ResponseEntity<?> loginMember(LoginFormDto loginFormDto) {
        JoinFormDto joinFormDto = memberService.loginMember(loginFormDto);
        if(joinFormDto == null) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(joinFormDto);
    }

    @GetMapping("/mainPage/{memberId}")
    public MainDto mainPage(@PathVariable("memberId") Long memberId) {
        MainDto mainDto = pillService.findPills(memberId);
    }

}

