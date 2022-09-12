package com.jack.currency.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SymbolDTO {
    private String code;

    private String desc;
}
