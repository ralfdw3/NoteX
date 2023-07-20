package com.notex.system.controller.impl;

import com.notex.system.controller.CardControllerInterface;
import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.dto.CardResponse;
import com.notex.system.service.impl.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/card")
@RequiredArgsConstructor
public class CardController implements CardControllerInterface {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardRequest request){
        return new ResponseEntity<CardResponse>(cardService.createCard(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CardResponse> updateCard(@RequestBody @Valid CardUpdateRequest request){
        return new ResponseEntity<CardResponse>(cardService.updateCard(request), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CardResponse> getCardById(@PathVariable("id") String id){
        return new ResponseEntity<CardResponse>(cardService.getCardById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCardById(@PathVariable("id") String id) {
        return new ResponseEntity(cardService.deleteCardById(id), HttpStatus.OK);
    }
}
