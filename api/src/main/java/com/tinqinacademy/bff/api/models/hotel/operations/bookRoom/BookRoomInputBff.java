package com.tinqinacademy.bff.api.models.hotel.operations.bookRoom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.models.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class BookRoomInputBff implements OperationInput {
    @Hidden
    private UUID roomId;
    @FutureOrPresent(message = "must be a valid date")
    private LocalDate startDate;
    @FutureOrPresent(message = "must be a valid date")
    private LocalDate endDate;
    @Size(min=1,max = 10)
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @Size(min=1,max = 10)
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @Size(min=1,max = 10)
    @NotNull(message = "phone number cannot be null")
    private String phoneNumber;
    @Past(message = "must a past date")
    private LocalDate birthdate;
    @NotBlank(message = "email cannot be blank")
    private String email;

    @JsonIgnore
    private String userId;
}
