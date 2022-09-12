package com.jack.currency.converter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jack.currency.converter.dto.SymbolDTO;
import com.jack.currency.converter.service.SymbolService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class SymbolController {
    @Autowired
    private SymbolService symbolService;

    @GetMapping("/api/symbol")
    public ResponseEntity<List<SymbolDTO>> getSymbolList() throws JsonProcessingException {
        return new ResponseEntity<>(symbolService.getSymbolList(), HttpStatus.OK);
    }
}
