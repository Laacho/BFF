package com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PartialUpdateRoomInputBff implements OperationInput {

    @Hidden
    private UUID id;
    private List<String> beds;
    @Size(min = 1, max = 15)
    @NotNull(message = "bathroom type cannot be null")
    private String bathroomType;
    @PositiveOrZero(message = "floor must be equal or greater to zero")
    @Max(10)
    @NotNull(message = "floor cannot be null")
    private Integer floor;
    @Size(min = 1, max = 15)
    @NotNull(message = "room number cannot be null")
    private String roomNumber;
    @PositiveOrZero
    @NotNull(message = "price cannot be null")
    private BigDecimal price;
}
