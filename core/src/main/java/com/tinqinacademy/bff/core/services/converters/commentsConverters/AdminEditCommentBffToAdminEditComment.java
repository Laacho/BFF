package com.tinqinacademy.bff.core.services.converters.commentsConverters;

import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentInputBff;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdminEditCommentBffToAdminEditComment implements Converter<AdminEditCommentInputBff, AdminEditCommentInput> {
    @Override
    public AdminEditCommentInput convert(AdminEditCommentInputBff source) {
        return AdminEditCommentInput.builder()
                .commentId(source.getCommentId())
                .content(source.getContent())

                .build();
    }
}
