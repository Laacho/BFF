package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsInputBff;
import com.tinqinacademy.hotel.api.models.operations.getFreeRooms.GetFreeRoomsInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GetFreeRoomsInputBffToGetFreeRoomsInput implements Converter<GetFreeRoomsInputBff, GetFreeRoomsInput> {

    @Override
    public GetFreeRoomsInput convert(GetFreeRoomsInputBff source) {
        return GetFreeRoomsInput.builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .bathRoomType(source.getBathRoomType())
                .beds(source.getBeds())
                .build();
    }
}