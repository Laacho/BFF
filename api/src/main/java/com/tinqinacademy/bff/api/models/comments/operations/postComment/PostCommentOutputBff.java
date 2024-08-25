package com.tinqinacademy.bff.api.models.comments.operations.postComment;


import com.tinqinacademy.bff.api.models.base.OperationOutput;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostCommentOutputBff implements  OperationOutput {
    @Size(min = 1,max=10)
    private  String id;
}
