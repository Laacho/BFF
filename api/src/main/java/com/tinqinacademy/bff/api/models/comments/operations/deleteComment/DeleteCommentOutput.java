package com.tinqinacademy.bff.api.models.comments.operations.deleteComment;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentOutput implements OperationOutput {
    private String message;
}
