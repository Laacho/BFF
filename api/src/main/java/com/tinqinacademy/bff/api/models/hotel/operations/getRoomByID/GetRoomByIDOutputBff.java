package com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import com.tinqinacademy.bff.api.models.hotel.enums.BathRoomType;
import com.tinqinacademy.bff.api.models.hotel.enums.Bed;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetRoomByIDOutputBff implements OperationOutput {
    private UUID id;
    private BigDecimal price;
    private Integer floor;
    private List<Bed> bed;
    private BathRoomType bathRoomType;
    private Map<LocalDate,LocalDate> datesOccupied;
}
