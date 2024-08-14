package com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeleteRoomOutputBff implements OperationOutput {
    private String message;
}
