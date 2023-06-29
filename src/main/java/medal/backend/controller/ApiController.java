package medal.backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import medal.backend.Dto.*;
import medal.backend.entity.Member;
import medal.backend.service.AlarmService;
import medal.backend.service.MemberService;
import medal.backend.service.PillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
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
    @ApiOperation(value = "아침, 점심, 저녁 별 알약 정보", notes = "아침: type=0 <br> 점심: type=1 <br> 저녁: type=2")
    @PostMapping("/info-alarm")
    public List<PillDto> mainPage(@RequestBody TypeDto typeDto, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");
        log.info("memberId: " + memberId);
        System.out.println(typeDto.getType());
        List<PillDto> pillDtoList = alarmService.findPills(typeDto.getType(), memberId);
        return pillDtoList;
    }

    @ApiOperation(value = "약 먹었는지 체크 요청", notes = "아침: type=0 <br> 점심: type=1 <br> 저녁: type=2")
    @PostMapping("/take-pill")
    public ResponseEntity<?> takePill(@RequestBody TypeDto typeDto, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");
        List<PillDto> pillDtoList = alarmService.findPills(typeDto.getType(), memberId);
        alarmService.takePill(typeDto.getType(), pillDtoList);
        return ResponseEntity.ok(true);
    }


}

