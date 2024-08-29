package com.tinqinacademy.bff.api.models.hotel.operations.createRoom;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateRoomOutputBff implements OperationOutput {
    private UUID id;
}
