package com.tinqinacademy.bff.api.models.comments.operations.adminEditComment;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminEditCommentOutput implements OperationOutput {
    private String id;
}
