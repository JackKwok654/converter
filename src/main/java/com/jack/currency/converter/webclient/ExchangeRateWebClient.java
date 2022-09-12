package com.jack.currency.converter.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.currency.converter.constant.OutputFormat;
import com.jack.currency.converter.dto.SymbolDTO;
import com.jack.currency.converter.exception.CommonException;
import com.jack.currency.converter.exception.ResponseFailException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Log4j2
@Component
public class ExchangeRateWebClient {
    WebClient client = WebClient.create("https://api.exchangerate.host");

    public List<SymbolDTO> getSymbols() throws JsonProcessingException, CommonException {
        final List<SymbolDTO> result = new ArrayList<>();

        final String responseJsonStr = getSymbolsApi(OutputFormat.JSON);

        log.info("[getSymbols] api response: {}", responseJsonStr);

        final Map<String, Object> responseJsonMap = new ObjectMapper().readValue(responseJsonStr, HashMap.class);

        if (!(boolean) responseJsonMap.get("success")) {
            throw new ResponseFailException("Api response fail");
        }

        LinkedHashMap<String, LinkedHashMap<String, String>> symbolsMap =
                (LinkedHashMap<String, LinkedHashMap<String, String>>) responseJsonMap.get("symbols");
        symbolsMap.values().forEach(v -> result.add(new SymbolDTO(v.get("code"), v.get("description"))));

        return result;
    }

    private String getSymbolsApi(OutputFormat format) {
        return client.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/symbols");
                    if (format != OutputFormat.JSON) {
                        uriBuilder.queryParam("format", format.name());
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
