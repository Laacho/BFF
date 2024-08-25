package com.tinqinacademy.bff.core.services.converters.commentsConverters;

import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsInputBff;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GetAllCommentsInputBffToGetAllComments implements Converter<GetAllCommentsInputBff, GetAllCommentsInput> {
    @Override
    public GetAllCommentsInput convert(GetAllCommentsInputBff source) {
        return GetAllCommentsInput.builder()
                .roomId(source.getRoomId())
                .build();
    }
}
