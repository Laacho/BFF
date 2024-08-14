package com.tinqinacademy.bff.api.models.comments.operations.editComment;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentOutput implements OperationOutput {

    private String id;
}
