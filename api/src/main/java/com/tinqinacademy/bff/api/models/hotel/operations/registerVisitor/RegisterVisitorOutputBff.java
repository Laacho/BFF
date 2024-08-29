package com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor;


import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegisterVisitorOutputBff implements  OperationOutput {
        private String message;
}
