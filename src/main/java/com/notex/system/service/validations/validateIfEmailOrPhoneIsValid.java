package com.notex.system.service.validations;

import com.notex.system.exceptions.BadRequestException;
import com.notex.system.models.Company.CompanyRequest;
import org.springframework.stereotype.Component;

@Component
public class validateIfEmailOrPhoneIsValid implements CompanyValidation{
    @Override
    public void validate(CompanyRequest companyRequest) {
        if (phoneAndEmailEmpty(companyRequest)){
            throw new BadRequestException("Pelo menos um e-mail ou telefone precisa ser adicionado");
        }
    }
    private static boolean phoneAndEmailEmpty(CompanyRequest companyRequest) {
        return companyRequest.getEmail().isEmpty() && companyRequest.getPhone().isEmpty();
    }
}
