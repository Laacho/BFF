package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIDInputBff;
import com.tinqinacademy.hotel.api.models.operations.getRoomByID.GetRoomByIDInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GetRoomByIDInputBffToGetRoomByIDInput implements Converter<GetRoomByIDInputBff, GetRoomByIDInput> {
    @Override
    public GetRoomByIDInput convert(GetRoomByIDInputBff source) {
        return GetRoomByIDInput.builder()
                .roomID(source.getRoomID())
                .build();
    }
}
