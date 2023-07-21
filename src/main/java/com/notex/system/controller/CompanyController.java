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
            @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar uma nova 'company'."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar uma nova 'company'.")
    })
    @Operation(description = "Cria uma nova 'company'")
    @PostMapping
    public ResponseEntity createCompany(@RequestBody @Valid CompanyRequest request){
        return new ResponseEntity(companyService.createCompany(request), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar a company."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar a company.")
    })
    @Operation(description = "Atualiza a company")
    @PutMapping
    public ResponseEntity updateCompany(@RequestBody @Valid CompanyUpdateRequest request){
        return new ResponseEntity(companyService.updateCompany(request), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar a company pelo id."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a company pelo id.")
    })
    @Operation(description = "Busca uma company no banco de dados pelo id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") String id){
        return new ResponseEntity(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o status da company."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o status da company.")
    })
    @Operation(description = "Habilita ou desabilita a company pelo status.")
    @PatchMapping
    public ResponseEntity updateCompanyStatus(@RequestParam String id, @RequestParam Boolean status){
        companyService.updateCompanyStatus(id, status);
        return new ResponseEntity("Empresa desabilitada.", HttpStatus.OK);
    }

}
