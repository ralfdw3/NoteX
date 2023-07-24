package com.notex.system.stubs;

import com.notex.system.models.Company;

import java.time.LocalDateTime;

public interface CompanyStub {
    static Company companyDefault() {
        return new Company("id", "name", "code", LocalDateTime.now(), true);
    }
}