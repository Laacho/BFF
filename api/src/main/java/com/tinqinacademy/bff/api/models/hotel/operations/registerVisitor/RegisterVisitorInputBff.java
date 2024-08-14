package com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterVisitorInputBff implements OperationInput {


    @FutureOrPresent(message = "must be a valid date")
    private LocalDate startDate;
    @FutureOrPresent(message = "must be a valid date")
    private LocalDate endDate;

    @NotNull(message = "room number must not be null")
    private String roomNumber;
    private List<DataForEachVisitorBff> dataForVisitors;

}
