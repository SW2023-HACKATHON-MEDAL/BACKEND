package medal.backend.service;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.MainDto;
import medal.backend.Dto.PillDto;
import medal.backend.entity.Pill;
import medal.backend.repository.PillRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
                .evening(pillDto.isEvening())
                .storeImgName(pillDto.getStoreImgName())
                .build();
        Pill savedpill = pillRepository.save(pill);
        return savedpill.getId();
    }

    public PillDto findPillById(Long id) {
        Pill pill = pillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return PillDto.builder().name(pill.getName())
                .color(pill.getColor())
                .texture(pill.getTexture())
                .shape(pill.getShape())
                .storeImgName(pill.getStoreImgName())
                .build();
    }

    public MainDto findPills(Long memberId) {
        pillRepository.findMorningPills(memberId).stream().
    }
}
