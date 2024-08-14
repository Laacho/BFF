package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomInputBff;
import com.tinqinacademy.hotel.api.models.operations.updateRoom.UpdateRoomInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoomInputBffToUpdateRoomInput implements Converter<UpdateRoomInputBff, UpdateRoomInput> {
    @Override
    public UpdateRoomInput convert(UpdateRoomInputBff source) {
        return UpdateRoomInput.builder()
                .id(source.getId())
                .price(source.getPrice())
                .floor(source.getFloor())
                .bathroomType(source.getBathroomType())
                .beds(source.getBeds())
                .roomNumber(source.getRoomNumber())
                .build();
    }
}
