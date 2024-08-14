package com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UnbookRoomOutputBff implements OperationOutput {
    private String message;
}
