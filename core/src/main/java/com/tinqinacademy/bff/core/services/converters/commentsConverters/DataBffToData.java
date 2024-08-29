package com.tinqinacademy.bff.core.services.converters.commentsConverters;

import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.DataOutputBff;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.DataOutput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBffToData implements Converter<List<DataOutput>, List<DataOutputBff>> {
    @Override
    public List<DataOutputBff> convert(List<DataOutput> source) {
        List<DataOutputBff> output = new ArrayList<>();
        for (DataOutput dataOutput : source) {
            DataOutputBff dataOutputBff = convertData(dataOutput);
            output.add(dataOutputBff);
        }
        return output;
    }

    private  DataOutputBff convertData(DataOutput dataOutputBff) {
        return DataOutputBff.builder()
                .id(dataOutputBff.getId())
                .firstName(dataOutputBff.getFirstName())
                .lastName(dataOutputBff.getLastName())
                .content(dataOutputBff.getContent())
                .publishedDate(dataOutputBff.getPublishedDate())
                .lastEditedDate(dataOutputBff.getLastEditedDate())
                .lastEditedBy(dataOutputBff.getLastEditedBy())
                .build();
    }
}
