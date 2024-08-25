package com.tinqinacademy.bff.core.services.converters.commentsConverters;

import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentInputBff;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeleteCommentBffToDeleteComment implements Converter<DeleteCommentInputBff, DeleteCommentInput> {
    @Override
    public DeleteCommentInput convert(DeleteCommentInputBff source) {
        return DeleteCommentInput.builder()
                .commentId(source.getCommentId())
                .build();
    }
}
