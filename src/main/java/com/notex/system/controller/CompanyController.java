package com.notex.system.controller;

import com.notex.system.dto.CompanyRequest;
import com.notex.system.dto.CompanyUpdateRequest;
import com.notex.system.enums.CompanyStatus;
import com.notex.system.service.impl.CompanyService;
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
@RequestMapping(path = "/v1/company")
@RequiredArgsConstructor
@CrossOrigin
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
    @PatchMapping
    public ResponseEntity updateCompany(@RequestBody @Valid CompanyUpdateRequest request){
        return new ResponseEntity(companyService.updateCompany(request), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar a empresa pelo id."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a empresa pelo id.")
    })
    @Operation(description = "Busca uma empresa no banco de dados pelo id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") String code){
        return new ResponseEntity(companyService.getCompanyById(code), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar a lista de empresas ativas."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a lista de empresas ativas.")
    })
    @Operation(description = "Busca a lista de empresas ativas.")
    @GetMapping(path = "/all/active")
    public ResponseEntity getAllActiveCompanies(@PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity(companyService.getAllActiveCompanies(pageable), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar a lista de empresas inadimplentes."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a lista de empresas inadimplentes.")
    })
    @Operation(description = "Busca a lista de empresas inadimplentes.")
    @GetMapping(path = "/all/overdue")
    public ResponseEntity getAllOverdueCompanies(@PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity(companyService.getAllOverdueCompanies(pageable), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar uma lista de empresas por uma substring."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a lista de empresas por uma substring.")
    })
    @Operation(description = "Busca uma lista de empresas no banco de dados por uma substring.")
    @GetMapping(path = "/actives")
    public ResponseEntity getCompanyBySearchTerm(@PageableDefault(size = 10) Pageable pageable, @RequestParam String searchTerm ){
        return new ResponseEntity(companyService.getCompaniesBySearchTerm(pageable, searchTerm), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar uma lista de empresas inadimplentes por uma substring."),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar a lista de empresas inadimplentes por uma substring.")
    })
    @Operation(description = "Busca uma lista de empresas inadimplentes no banco de dados por uma substring.")
    @GetMapping(path = "/overdues")
    public ResponseEntity getOverdueCompanyBySearchTerm(@PageableDefault(size = 10) Pageable pageable, @RequestParam String searchTerm ){
        return new ResponseEntity(companyService.getOverdueCompaniesBySearchTermAndStatus(pageable, searchTerm), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o status da empresa."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o status da empresa.")
    })
    @Operation(description = "Habilita ou desabilita a empresa pelo status.")
    @DeleteMapping
    public ResponseEntity disableCompany(@RequestParam String code){
        return new ResponseEntity(companyService.disableCompany(code), HttpStatus.OK);
    }

}
