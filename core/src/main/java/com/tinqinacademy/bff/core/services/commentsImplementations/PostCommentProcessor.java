package com.tinqinacademy.bff.core.services.commentsImplementations;

import com.tinqinacademy.bff.api.models.comments.operations.postComment.PostCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.postComment.PostCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.postComment.PostCommentOutputBff;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentOutput;
import com.tinqinacademy.comments.restexport.RestExportComments;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import feign.FeignException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Service
@Slf4j
public class PostCommentProcessor implements PostCommentOperation {
    private final ConversionService conversionService;
    private final RestExportComments restExportComments;
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;

    public PostCommentProcessor(ConversionService conversionService, RestExportComments restExportComments, ErrorHandler errorHandler, RestExportHotel restExportHotel) {
        this.conversionService = conversionService;
        this.restExportComments = restExportComments;
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
    }

    @Override
    public Either<ErrorWrapper, PostCommentOutputBff> process(PostCommentInputBff input) {
        return Try.of(()->{
                    restExportHotel.getRoomsById(UUID.fromString(input.getRoomId()));
                    PostCommentInput convert = conversionService.convert(input, PostCommentInput.class);
                    PostCommentOutput output = restExportComments.postComments(convert.getRoomId(), convert);
                   log.info("End postComment output: {}", output);
                    PostCommentOutputBff outputBff = PostCommentOutputBff.builder()
                            .id(output.getId())
                            .build();
                    log.info("End postComment outputBff: {}", outputBff);
                   return outputBff;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }
}