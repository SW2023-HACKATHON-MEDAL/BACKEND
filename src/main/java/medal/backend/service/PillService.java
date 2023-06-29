package medal.backend.service;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.PillDto;
import medal.backend.entity.Pill;
import medal.backend.repository.PillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PillService {

    private final PillRepository pillRepository;

    public Long savePill(PillDto pillDto) {
        
        Pill pill = Pill.builder().name(pillDto.getName())
                .shape(pillDto.getShape())
                .color(pillDto.getColor())
                .texture(pillDto.getTexture())
                .storeImgName(pillDto.getStoreImgName())
                .build();
        Pill savedpill = pillRepository.save(pill);
        return savedpill.getId();
    }

}
