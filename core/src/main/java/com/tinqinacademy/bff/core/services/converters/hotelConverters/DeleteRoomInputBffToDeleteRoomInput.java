package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomInputBff;
import com.tinqinacademy.hotel.api.models.operations.deleteRoom.DeleteRoomInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeleteRoomInputBffToDeleteRoomInput implements Converter<DeleteRoomInputBff, DeleteRoomInput> {
    @Override
    public DeleteRoomInput convert(DeleteRoomInputBff source) {
        return DeleteRoomInput.builder()
                .roomId(source.getRoomId())
                .build();
    }
}
