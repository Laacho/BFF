package com.tinqinacademy.bff.core.services.commentsImplementations;

import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentOutputBff;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentInput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOutput;
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
public class DeleteCommentProcessor implements DeleteCommentOperation {

    private final ErrorHandler errorHandler;
    private final ConversionService conversionService;
    private final RestExportComments restExportComments;

    public DeleteCommentProcessor(ErrorHandler errorHandler, ConversionService conversionService, RestExportComments restExportComments) {
        this.errorHandler = errorHandler;
        this.conversionService = conversionService;
        this.restExportComments = restExportComments;
    }

    @Override
    public Either<ErrorWrapper, DeleteCommentOutputBff> process(DeleteCommentInputBff input) {
            log.info("Start deletingComment input: {}",input);
        return   Try.of(()->{

                    DeleteCommentInput convert = conversionService.convert(input, DeleteCommentInput.class);
                    DeleteCommentOutput output = restExportComments.deleteComment(convert.getCommentId());
                    log.info("End deletingComment output: {}", output);
                    DeleteCommentOutputBff outputBff = DeleteCommentOutputBff.builder()
                            .message(output.getMessage())
                            .build();
                        log.info("End deletingCommentBff output: {}", outputBff);
                   return outputBff;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }
}
