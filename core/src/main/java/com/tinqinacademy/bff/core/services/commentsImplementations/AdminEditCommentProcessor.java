package com.tinqinacademy.bff.core.services.commentsImplementations;

import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentOutputBff;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
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
public class AdminEditCommentProcessor implements AdminEditCommentOperation {
    private final ErrorHandler errorHandler;
    private final ConversionService conversionService;
    private final RestExportComments  restExportComments;
    private final RestExportHotel restExportHotel;

    public AdminEditCommentProcessor(ErrorHandler errorHandler, ConversionService conversionService, RestExportComments restExportComments, RestExportHotel restExportHotel) {
        this.errorHandler = errorHandler;
        this.conversionService = conversionService;
        this.restExportComments = restExportComments;
        this.restExportHotel = restExportHotel;
    }

    @Override
    public Either<ErrorWrapper, AdminEditCommentOutputBff> process(AdminEditCommentInputBff input) {
        log.info("Start AdminEditComment input: {}", input);
        return   Try.of(()->{
                    checkIfRoomIdIsValid(input);
                    AdminEditCommentInput convert = conversionService.convert(input, AdminEditCommentInput.class);
                    AdminEditCommentOutput output = restExportComments.adminEditComment(input.getCommentId(), convert);
                    log.info("End adminEditCommentOutput: {}", output);
                    AdminEditCommentOutputBff outputBff = AdminEditCommentOutputBff.builder()
                            .id(output.getId())
                            .build();
                    log.info("End adminEditCommentOutputBff: {}", outputBff);
                    return outputBff;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }
    public void checkIfRoomIdIsValid(AdminEditCommentInputBff input) {
        if(input.getRoomId()!=null){
            restExportHotel.getRoomsById(UUID.fromString(input.getRoomId()));
        }
    }
}
