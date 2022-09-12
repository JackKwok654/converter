package com.jack.currency.converter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jack.currency.converter.dto.SymbolDTO;
import com.jack.currency.converter.exception.CommonException;

import java.util.List;

public interface SymbolService {
    List<SymbolDTO> getSymbolList() throws CommonException, JsonProcessingException;
}
