package com.tinqinacademy.bff.api.models.hotel.operations.createRoom;


import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateRoomInputBff implements OperationInput {

    private List<String> beds;
    @NotEmpty
    private String bathRoomType;
    @PositiveOrZero(message = "floor must be equal or greater to zero")
    @NotNull(message = "floor cannot be null")
    private Integer floor;
    @Size(min = 1, max = 5)
    @NotNull(message = "room number cannot be null")
    private String roomNumber;
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 3, fraction = 2)
    @NotNull(message = "price cannot be null")
    private BigDecimal price;

}
