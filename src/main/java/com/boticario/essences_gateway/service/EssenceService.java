package com.boticario.essences_gateway.service;

import com.boticario.essences_gateway.client.EssencesClient;
import com.boticario.essences_gateway.dto.EssenceDetailDTO;
import com.boticario.essences_gateway.dto.EssenceDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EssenceService {

    private final EssencesClient essencesClient;

    @Cacheable(value = "essences")
    @RateLimiter(name = "essences-api")
    public List<EssenceDTO> getEssences() {
        return essencesClient.getEssences();
    }

    @Cacheable(value = "essenceDetail", key = "#essenceId")
    @RateLimiter(name = "essences-api")
    public EssenceDetailDTO getEssenceDetail(String essenceId) {
        return essencesClient.getEssenceDetail(essenceId);
    }
}