package com.notex.system.service.validations;

import com.notex.system.exceptions.BadRequestException;
import com.notex.system.models.Company.CompanyRequest;
import org.springframework.stereotype.Component;

@Component
public class validateIfCompanyNameIsValid implements CompanyValidation{
    @Override
    public void validate(CompanyRequest companyRequest) {
        if (companyNameNullOrEmpty(companyRequest)) {
            throw new BadRequestException("Coloque o nome da empresa para cadastrá-la, pois não existe nenhuma empresa com este código.");
        }
    }
    private static boolean companyNameNullOrEmpty(CompanyRequest companyRequest) {
        return companyRequest.getName() == null || companyRequest.getName().isEmpty();
    }
}
