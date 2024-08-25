package com.tinqinacademy.bff.core.services.converters.commentsConverters;

import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentInputBff;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EditCommentIBffToEditComment implements Converter<EditCommentInputBff, EditCommentInput> {
    @Override
    public EditCommentInput convert(EditCommentInputBff source) {
        return EditCommentInput.builder()
                .content(source.getContent())
                .id(source.getId())
                .build();
    }
}
