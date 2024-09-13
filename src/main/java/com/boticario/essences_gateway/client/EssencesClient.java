package com.boticario.essences_gateway.client;

import com.boticario.essences_gateway.dto.EssenceDetailDTO;
import com.boticario.essences_gateway.config.FeignClientConfig;
import com.boticario.essences_gateway.dto.EssenceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "${essences.client.name}", url = "${essences.client.url}", configuration = FeignClientConfig.class)
public interface EssencesClient {


    @GetMapping("/essences")
    List<EssenceDTO> getEssences();

    @GetMapping("/essences/{essence-id}")
    EssenceDetailDTO getEssenceDetail(@PathVariable("essence-id") String essenceId);
}