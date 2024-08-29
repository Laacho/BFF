package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomInputBff;
import com.tinqinacademy.hotel.api.models.operations.partialUpdateRoom.PartialUpdateRoomInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PartialUpdateBffToPartialUpdate implements Converter<PartialUpdateRoomInputBff, PartialUpdateRoomInput> {
    @Override
    public PartialUpdateRoomInput convert(PartialUpdateRoomInputBff source) {
        return PartialUpdateRoomInput.builder()
                .id(source.getId())
                .floor(source.getFloor())
                .price(source.getPrice())
                .beds(source.getBeds())
                .bathroomType(source.getBathroomType())
                .roomNumber(source.getRoomNumber())
                .build();
    }
}
