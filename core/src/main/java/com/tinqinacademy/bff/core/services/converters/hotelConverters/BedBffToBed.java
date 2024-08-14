package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.hotel.api.models.enums.Bed;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BedBffToBed implements Converter<List<Bed>,List<com.tinqinacademy.bff.api.models.hotel.enums.Bed>> {
    @Override
    public List<com.tinqinacademy.bff.api.models.hotel.enums.Bed> convert(List<Bed> source) {
        List<com.tinqinacademy.bff.api.models.hotel.enums.Bed> result=new ArrayList<>();
        for (Bed bed : source) {
            com.tinqinacademy.bff.api.models.hotel.enums.Bed byCode =
                    com.tinqinacademy.bff.api.models.hotel.enums.Bed.getByCode(bed.toString());
            result.add(byCode);
        }
        return result;
    }
}
