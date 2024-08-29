package com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom;


import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeleteRoomInputBff implements OperationInput {
    @NotNull(message = "room cannot be null")
    private UUID roomId;
}
