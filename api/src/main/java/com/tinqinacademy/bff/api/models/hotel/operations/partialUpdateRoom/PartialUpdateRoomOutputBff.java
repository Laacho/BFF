package com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PartialUpdateRoomOutputBff implements  OperationOutput {
    private UUID id;
}
