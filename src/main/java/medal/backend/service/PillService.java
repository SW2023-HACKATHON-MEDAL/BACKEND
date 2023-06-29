package medal.backend.service;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.MainDto;
import medal.backend.Dto.PillDto;
import medal.backend.entity.Pill;
import medal.backend.repository.PillRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PillService {

    private final PillRepository pillRepository;

    public Long savePill(PillDto pillDto) {
        
        Pill pill = Pill.builder().name(pillDto.getName())
                .shape(pillDto.getShape())
                .color(pillDto.getColor())
                .texture(pillDto.getTexture())
                .morning(pillDto.isMorning()) //아점저 정보
                .dinner(pillDto.isDinner())
                .launch(pillDto.isLaunch())
                .storeImgName(pillDto.getStoreImgName())
                .build();
        Pill savedpill = pillRepository.save(pill);
        return savedpill.getId();
    }

    /**
     * 메인 화면
     */
    public MainDto findPills(Long memberId) {
        List<PillDto> morningPills = pillRepository.findMorningPills(memberId)
                .stream().map(pill -> new PillDto(pill)).collect(Collectors.toList());
        List<PillDto> launchPills = pillRepository.findLaunchPills(memberId)
                .stream().map(pill -> new PillDto(pill)).collect(Collectors.toList());
        List<PillDto> dinnerPills = pillRepository.findDinnerPills(memberId)
                .stream().map(pill -> new PillDto(pill)).collect(Collectors.toList());

        MainDto mainDto = new MainDto();
        mainDto.setMorningPills(morningPills);
        mainDto.setLaunchPills(launchPills);
        mainDto.setDinnerPills(dinnerPills);
        return mainDto;
    }
}
