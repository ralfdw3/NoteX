package com.notex.system.controller;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.service.impl.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar uma nova empresa."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar uma nova empresa.")
    })
    @Operation(description = "Cria uma nova empresa")
    @PostMapping
    public ResponseEntity createCompany(@RequestBody @Valid CompanyRequest request){
        return new ResponseEntity(companyService.createCompany(request), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar a empresa."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar a empresa.")
    })
    @Operation(description = "Atualiza a empresa")
    @PutMapping
    public ResponseEntity updateCompany(@RequestBody @Valid CompanyUpdateRequest request){
        return new ResponseEntity(companyService.updateCompany(request), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar a empresa pelo id."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a empresa pelo id.")
    })
    @Operation(description = "Busca uma empresa no banco de dados pelo id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") String id){
        return new ResponseEntity(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar uma lista de empresas por uma substring."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a lista de empresas por uma substring.")
    })
    @Operation(description = "Busca uma lista de empresas no banco de dados por uma substring.")
    @GetMapping
    public ResponseEntity getCompanyBySearchTerm(@RequestParam String searchTerm){
        return new ResponseEntity(companyService.getCompaniesBySearchTerm(searchTerm), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o status da empresa."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o status da empresa.")
    })
    @Operation(description = "Habilita ou desabilita a empresa pelo status.")
    @PatchMapping
    public ResponseEntity updateCompanyStatus(@RequestParam String id, @RequestParam Boolean status){
        companyService.updateCompanyStatus(id, status);
        return new ResponseEntity("Empresa desabilitada.", HttpStatus.OK);
    }

}
