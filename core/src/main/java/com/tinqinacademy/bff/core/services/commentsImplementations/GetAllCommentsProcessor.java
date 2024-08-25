package com.tinqinacademy.bff.core.services.commentsImplementations;

import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.DataOutputBff;
import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsOperation;
import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsOutputBff;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.core.services.converters.commentsConverters.DataBffToData;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsInput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOutput;
import com.tinqinacademy.comments.restexport.RestExportComments;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GetAllCommentsProcessor implements GetAllCommentsOperation {
    private final ErrorHandler errorHandler;
    private final ConversionService conversionService;
    private final RestExportComments restExportComments;
    private final DataBffToData dataBffToData;

    public GetAllCommentsProcessor(ErrorHandler errorHandler, ConversionService conversionService, RestExportComments restExportComments, DataBffToData dataBffToData) {
        this.errorHandler = errorHandler;
        this.conversionService = conversionService;
        this.restExportComments = restExportComments;
        this.dataBffToData = dataBffToData;
    }

    @Override
    public Either<ErrorWrapper, GetAllCommentsOutputBff> process(GetAllCommentsInputBff input) {
        return Try.of(()->{
                    GetAllCommentsInput convert = conversionService.convert(input, GetAllCommentsInput.class);
                    GetAllCommentsOutput output = restExportComments.getAllComments(convert.getRoomId());
                    log.info("End getAllComments output: {}", output);
                    List<DataOutputBff> resultData = dataBffToData.convert(output.getComments());
                    GetAllCommentsOutputBff outputBff = GetAllCommentsOutputBff.builder()
                            .comments(resultData)
                            .build();
                    log.info("End getAllComments outputBff: {}", outputBff);
                    return outputBff;
                }).toEither()
                .mapLeft(errorHandler::handleError);
    }
}
