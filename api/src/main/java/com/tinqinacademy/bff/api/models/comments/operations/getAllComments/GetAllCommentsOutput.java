package com.tinqinacademy.bff.api.models.comments.operations.getAllComments;

import com.tinqinacademy.bff.api.models.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetAllCommentsOutput implements  OperationOutput {
   private List<DataOutput> comments;
}
