package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomInputBff;
import com.tinqinacademy.hotel.api.models.operations.unbookRoom.UnbookRoomInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnbookRoomBffToUnbookRoom implements Converter<UnbookRoomInputBff, UnbookRoomInput> {
    @Override
    public UnbookRoomInput convert(UnbookRoomInputBff source) {
        return UnbookRoomInput.builder()
                .bookingRoomId(source.getBookingRoomId())
                .build();
    }
}
