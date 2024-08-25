package com.tinqinacademy.bff.api.models.comments.operations.postComment;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PostCommentInputBff implements OperationInput {

    @Hidden
    private String roomId;
    @Size(min = 1,max = 10)
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @Size(min = 1,max = 10)
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @Size(min = 1,max = 1000)
    @NotNull(message = "content cannot be null")
    private String content;

}