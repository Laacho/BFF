package com.tinqinacademy.bff.api.models.comments.operations.getAllComments;


import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCommentsInput implements OperationInput {

    @NotBlank(message = "room id cannot be empty/blank")
    private String roomId;
}
