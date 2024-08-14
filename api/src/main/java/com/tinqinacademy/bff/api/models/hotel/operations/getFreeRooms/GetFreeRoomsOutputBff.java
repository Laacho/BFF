package com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetFreeRoomsOutputBff implements  OperationOutput {
    private List<UUID> ids;
}
