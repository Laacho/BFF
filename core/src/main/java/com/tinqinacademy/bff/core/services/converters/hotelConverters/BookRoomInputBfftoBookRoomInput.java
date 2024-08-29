package com.tinqinacademy.bff.core.services.converters.hotelConverters;

import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomInputBff;
import com.tinqinacademy.hotel.api.models.operations.bookRoom.BookRoomInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookRoomInputBfftoBookRoomInput implements Converter<BookRoomInputBff, BookRoomInput> {
    @Override
    public BookRoomInput convert(BookRoomInputBff input) {
       return  BookRoomInput.builder()
                .roomId(input.getRoomId())
                .birthdate(input.getBirthdate())
                .email(input.getEmail())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .build();
    }
}
