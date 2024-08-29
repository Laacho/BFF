package com.tinqinacademy.bff.api.models.hotel.operations.bookRoom;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookRoomOutputBff implements  OperationOutput {
    private String message;
}
