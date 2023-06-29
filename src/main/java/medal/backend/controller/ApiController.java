package medal.backend.controller;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.*;
import medal.backend.entity.Member;
import medal.backend.service.AlarmService;
import medal.backend.service.MemberService;
import medal.backend.service.PillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final PillService pillService;
    private final MemberService memberService;
    private final AlarmService alarmService;

    /**
     * 알람 등록 (약국 데이터에서 자동으로 해준다고 가정)
     */
    @PostMapping("/enroll-alarm")
    public ResponseEntity<?> enrollAlarm(AlarmFromDto alarmFromDto, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");
        alarmService.enrollAlarm(alarmFromDto, memberId);
        return ResponseEntity.ok(true);
    }

    /**
     * 아침, 점심, 저녁 별 알약 정보
     * 0: 아침
     * 1: 점심
     * 2: 저녁
     */
    @GetMapping("/info-page")
    public List<PillDto> mainPage(@RequestParam("type") int when, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");
        List<PillDto> pillDtoList = alarmService.findPills(when, memberId);
        return pillDtoList;
    }



}

