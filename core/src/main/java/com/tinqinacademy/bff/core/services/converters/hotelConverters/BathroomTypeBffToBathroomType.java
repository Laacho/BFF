package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.hotel.api.models.enums.BathRoomType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BathroomTypeBffToBathroomType implements Converter<BathRoomType, com.tinqinacademy.bff.api.models.hotel.enums.BathRoomType> {
    @Override
    public com.tinqinacademy.bff.api.models.hotel.enums.BathRoomType convert(BathRoomType source) {
        return com.tinqinacademy.bff.api.models.hotel.enums.BathRoomType.getByCode(source.toString());
    }
}
