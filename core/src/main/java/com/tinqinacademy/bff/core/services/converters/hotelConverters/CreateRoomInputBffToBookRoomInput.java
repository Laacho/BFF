package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomInputBff;
import com.tinqinacademy.hotel.api.models.operations.createRoom.CreateRoomInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateRoomInputBffToBookRoomInput implements Converter<CreateRoomInputBff, CreateRoomInput> {
    @Override
    public CreateRoomInput convert(CreateRoomInputBff source) {

        return CreateRoomInput.builder()
                .bathRoomType(source.getBathRoomType())
                .beds(source.getBeds())
                .floor(source.getFloor())
                .roomNumber(source.getRoomNumber())
                .price(source.getPrice())
                .build();
    }
}
