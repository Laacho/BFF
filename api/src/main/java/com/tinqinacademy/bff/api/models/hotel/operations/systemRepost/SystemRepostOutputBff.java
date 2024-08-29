package com.tinqinacademy.bff.api.models.hotel.operations.systemRepost;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SystemRepostOutputBff implements OperationOutput {
    private LocalDate startDate;
    private LocalDate endDate;
   private List<Data> data;
}
