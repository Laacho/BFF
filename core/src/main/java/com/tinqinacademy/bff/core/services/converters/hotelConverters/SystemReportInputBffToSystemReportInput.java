package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemReportInputBff;
import com.tinqinacademy.hotel.api.models.operations.systemRepost.SystemReportInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SystemReportInputBffToSystemReportInput implements Converter<SystemReportInputBff, SystemReportInput> {
    @Override
    public SystemReportInput convert(SystemReportInputBff source) {
        return SystemReportInput.builder()
                .phoneNumber(source.getPhoneNumber())
                .birthdate(source.getBirthdate())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardIssueDate(source.getIdCardIssueDate())
                .idCardNumber(source.getIdCardNumber())
                .idCardValidity(source.getIdCardValidity())
                .roomNumber(source.getRoomNumber())
                .build();
    }
}
