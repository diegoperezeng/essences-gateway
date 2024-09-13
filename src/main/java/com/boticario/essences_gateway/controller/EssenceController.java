package com.boticario.essences_gateway.controller;

import com.boticario.essences_gateway.dto.EssenceDetailDTO;
import com.boticario.essences_gateway.dto.EssenceDTO;
import com.boticario.essences_gateway.service.EssenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EssenceController {

    private final EssenceService essenceService;

    @GetMapping("/essences")
    public List<EssenceDTO> getEssences() {
        return essenceService.getEssences();
    }

    @GetMapping("/essences/{id}")
    public EssenceDetailDTO getEssenceDetail(@PathVariable("id") String essenceId) {
        return essenceService.getEssenceDetail(essenceId);
    }
}