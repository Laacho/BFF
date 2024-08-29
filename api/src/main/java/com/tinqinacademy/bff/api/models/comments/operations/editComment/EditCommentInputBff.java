package com.tinqinacademy.bff.api.models.comments.operations.editComment;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class EditCommentInputBff implements OperationInput {
    @Size(min = 1)
    @NotNull(message = "content cannot be null")
    private String content;

    @JsonIgnore
    private UUID commentId;

    @JsonIgnore
    private String userId;
}


