package com.tinqinacademy.bff.core.services.commentsImplementations;

import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentOutputBff;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.restexport.RestExportComments;
import feign.FeignException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;


@Service
@Slf4j
public class EditCommentProcessor implements EditCommentOperation {
    private final ErrorHandler errorHandler;
    private final ConversionService conversionService;
    private final RestExportComments restExportComments;

    public EditCommentProcessor(ErrorHandler errorHandler, ConversionService conversionService, RestExportComments restExportComments) {
        this.errorHandler = errorHandler;
        this.conversionService = conversionService;
        this.restExportComments = restExportComments;
    }

    @Override
    public Either<ErrorWrapper, EditCommentOutputBff> process(EditCommentInputBff input) {
        return   Try.of(()->{
                    EditCommentInput convert = conversionService.convert(input, EditCommentInput.class);
                    EditCommentOutput output = restExportComments.editComment(String.valueOf(convert.getId()), convert.getContent());
                    log.info("End adminEditCommentOutput: {}", output);
                    EditCommentOutputBff outputBff = EditCommentOutputBff.builder()
                            .id(output.getId())
                            .build();
                    log.info("End EditCommentOutputBff output: {}", outputBff);
                    return outputBff;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }
}
