package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.hotel.api.models.operations.systemRepost.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataToDataBff implements Converter<List<Data>,List< com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.Data>> {

    @Override
    public List<com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.Data> convert(List<Data> source) {
        List<com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.Data> list = new ArrayList<>();
        for (Data data : source) {
            com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.Data temp = com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.Data.builder()
                    .firstName(data.getFirstName())
                    .lastName(data.getLastName())
                    .phoneNumber(data.getPhoneNumber())
                    .idCardNumber(data.getIdCardNumber())
                    .idCardValidity(data.getIdCardValidity())
                    .idCardIssueAuthority(data.getIdCardIssueAuthority())
                    .idCardIssueDate(data.getIdCardIssueDate())
                    .birthdate(data.getBirthdate())
                    .build();
            list.add(temp);
        }
        return list;
    }
}