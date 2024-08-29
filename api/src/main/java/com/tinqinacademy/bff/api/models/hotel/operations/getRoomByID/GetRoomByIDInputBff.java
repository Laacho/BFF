package com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetRoomByIDInputBff implements OperationInput {
    @NotNull(message = "room id cannot be null")
    private UUID roomID;
}
