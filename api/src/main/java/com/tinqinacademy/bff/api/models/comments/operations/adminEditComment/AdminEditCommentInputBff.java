package com.tinqinacademy.bff.api.models.comments.operations.adminEditComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.models.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class AdminEditCommentInputBff implements OperationInput {
    @JsonIgnore
    private String commentId;
    @NotBlank(message = "room number cannot be blank")
    private String roomId;
    @JsonIgnore
    private UUID userId;
    @Size(min = 1,max = 255)
    @NotNull(message = "content cannot be empty")
    private String content;
}
