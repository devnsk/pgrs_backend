//package org.pgrs.config;
//
//import java.util.List;
//
//import org.pgrs.util.MultiDomainEmailValidator;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MultiDomainEmailValidationConfig {
//    @Bean
//    public MultiDomainEmailValidator multiDomainEmailValidator(
//        @Value("${college.email.domains}") List<String> validDomains
//    ) {
//        return new MultiDomainEmailValidator(validDomains);
//    }
//}