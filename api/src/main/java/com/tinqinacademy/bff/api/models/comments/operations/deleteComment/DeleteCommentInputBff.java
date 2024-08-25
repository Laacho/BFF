package com.tinqinacademy.bff.api.models.comments.operations.deleteComment;

import com.tinqinacademy.bff.api.models.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentInputBff implements OperationInput {
    @Hidden
    private String commentId;

}
