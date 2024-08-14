package com.tinqinacademy.bff.api.models.hotel.operations.updateRoom;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateRoomOutputBff implements  OperationOutput {
    private UUID id;
}
