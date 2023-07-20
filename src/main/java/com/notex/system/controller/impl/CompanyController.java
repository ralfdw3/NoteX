package com.notex.system.controller.impl;

import com.notex.system.controller.CompanyControllerInterface;
import com.notex.system.service.impl.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/company")
@RequiredArgsConstructor
public class CompanyController implements CompanyControllerInterface {

    private final CompanyService companyService;

}
