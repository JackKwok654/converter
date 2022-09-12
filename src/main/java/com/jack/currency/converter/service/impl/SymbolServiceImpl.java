package com.jack.currency.converter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jack.currency.converter.dto.SymbolDTO;
import com.jack.currency.converter.exception.CommonException;
import com.jack.currency.converter.exception.ResponseFailException;
import com.jack.currency.converter.service.SymbolService;
import com.jack.currency.converter.webclient.ExchangeRateWebClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class SymbolServiceImpl implements SymbolService {
    @Autowired
    private ExchangeRateWebClient exchangerateWebClient;

    @Override
    public List<SymbolDTO> getSymbolList() throws CommonException, JsonProcessingException {
        List<SymbolDTO> result;

        try {
            result = exchangerateWebClient.getSymbols();
        }catch (ResponseFailException | JsonProcessingException e) {
            throw e;
        } catch (Exception unknown) {
            log.error("[getSymbols] Unknown exception found: ", unknown);
            throw new CommonException("Unknown exception");
        }

        return result;
    }
}
