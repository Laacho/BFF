package com.tinqinacademy.bff.api.models.comments.operations.editComment;


import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentInput implements OperationInput {
    @Size(min = 1)
    @NotNull(message = "content cannot be null")
    private String content;

    @NotBlank(message = "id cannot be empty/blank")
    private UUID id;
}

