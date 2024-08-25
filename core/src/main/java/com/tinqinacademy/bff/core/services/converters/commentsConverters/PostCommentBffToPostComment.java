package com.tinqinacademy.bff.core.services.converters.commentsConverters;

import com.tinqinacademy.bff.api.models.comments.operations.postComment.PostCommentInputBff;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostCommentBffToPostComment implements Converter<PostCommentInputBff, PostCommentInput> {
    @Override
    public PostCommentInput convert(PostCommentInputBff source) {
        return PostCommentInput.builder()
                .roomId(source.getRoomId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .content(source.getContent())
                .build();
    }
}
