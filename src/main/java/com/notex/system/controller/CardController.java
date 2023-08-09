package com.notex.system.controller;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardUpdateRequest;
import com.notex.system.service.impl.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/card")
@RequiredArgsConstructor
@CrossOrigin
public class CardController {

    private final CardService cardService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar um novo card."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar um novo card.")
    })
    @Operation(description = "Cria um novo card, vinculando o mesmo a uma 'company'")
    @PostMapping
    public ResponseEntity createCard(@RequestBody @Valid CardRequest request){
        return new ResponseEntity(cardService.createCard(request), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o card."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o card.")
    })
    @Operation(description = "Atualiza o card específico")
    @PatchMapping
    public ResponseEntity updateCard(@RequestBody @Valid CardUpdateRequest request){
        return new ResponseEntity(cardService.updateCard(request), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar o card."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar o card.")
    })
    @Operation(description = "Busca um card no banco de dados")
    @GetMapping(path = "/{id}")
    public ResponseEntity getCardById(@PathVariable("id") String id){
        return new ResponseEntity(cardService.getCardById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar a lista de cards ativos."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a lista de cards ativos.")
    })
    @Operation(description = "Busca a lista de cards ativos")
    @GetMapping(path = "/all/active")
    public ResponseEntity getAllActiveCards(){
        return new ResponseEntity(cardService.getAllActiveCards(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar todos os cards de uma empresa."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar todos os cards de uma empresa.")
    })
    @Operation(description = "Busca todos os cards de uma empresa.")
    @GetMapping(path = "/all/")
    public ResponseEntity getAllCardsByCompany(@PageableDefault(size = 10) Pageable pageable, @RequestParam String companyId){
        return new ResponseEntity(cardService.getAllCardsByCompany(pageable, companyId), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta o card pelo id."),
            @ApiResponse(responseCode = "404", description = "Erro a encontrar o card que deve ser deletado.")
    })
    @Operation(description = "Deleta um card do banco de dados")
    @DeleteMapping
    public ResponseEntity deleteCardById(@RequestParam String id) {
        return new ResponseEntity(cardService.deleteCardById(id), HttpStatus.OK);
    }

}
