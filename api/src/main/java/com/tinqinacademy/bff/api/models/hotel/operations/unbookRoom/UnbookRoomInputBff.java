package com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom;


import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UnbookRoomInputBff implements OperationInput {
    @NotNull(message = "booking id cannot be null")
    private UUID bookingRoomId;
}
