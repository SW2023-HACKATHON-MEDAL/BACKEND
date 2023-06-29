package medal.backend.controller;

import lombok.AllArgsConstructor;
import medal.backend.PillDto;
import medal.backend.service.PillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ApiController {

    private final PillService pillService;

    @PostMapping("/save")
    public Long savePill(PillDto pillDto) {
        Long savedId = pillService.savePill(pillDto);
        return savedId;
    }

    @GetMapping("/find/{id}")
    public PillDto findPill(@PathVariable("id") Long id) {
        PillDto pillDto = pillService.findPillById(id);
        return pillDto;
    }

}

