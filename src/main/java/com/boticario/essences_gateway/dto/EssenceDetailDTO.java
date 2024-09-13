package com.boticario.essences_gateway.dto;

import lombok.Data;

import java.util.List;

@Data
public class EssenceDetailDTO {
    private String id;
    private String name;
    private List<String> values;
}