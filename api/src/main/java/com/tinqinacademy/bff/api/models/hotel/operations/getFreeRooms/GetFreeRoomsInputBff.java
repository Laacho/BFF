package com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetFreeRoomsInputBff implements OperationInput {
    @FutureOrPresent(message = "must be valid date")
    private LocalDate startDate;
    @FutureOrPresent(message = "must be valid date")
    private LocalDate endDate;
    private List<String> beds;
    @NotBlank(message = "cannot be null/blank")
    private String bathRoomType;
}
