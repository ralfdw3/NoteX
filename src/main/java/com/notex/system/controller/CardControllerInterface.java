package com.notex.system.controller;

import com.notex.system.dto.CardRequest;
import com.notex.system.dto.CardUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface CardControllerInterface {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar um novo card."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar um novo card.")
    })
    @Operation(description = "Cria um novo card, vinculando o mesmo a uma 'company'")
    ResponseEntity createCard(CardRequest request);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o card."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o card.")
    })
    @Operation(description = "Atualiza um card")
    ResponseEntity updateCard(CardUpdateRequest request);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar o card."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar o card.")
    })
    @Operation(description = "Busca um card no banco de dados")
    ResponseEntity getCardById(String id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta o card pelo id."),
            @ApiResponse(responseCode = "404", description = "Erro a encontrar o card que deve ser deletado.")
    })
    @Operation(description = "Deleta um card do banco de dados")
    ResponseEntity deleteCardById(String id);
}
