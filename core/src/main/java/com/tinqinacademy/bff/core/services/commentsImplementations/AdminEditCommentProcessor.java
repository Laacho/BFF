package com.tinqinacademy.bff.core.services.commentsImplementations;

import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentOutputBff;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
import com.tinqinacademy.comments.restexport.RestExportComments;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminEditCommentProcessor implements AdminEditCommentOperation {
    private final ErrorHandler errorHandler;
    private final ConversionService conversionService;
    private final RestExportComments  restExportComments;

    public AdminEditCommentProcessor(ErrorHandler errorHandler, ConversionService conversionService, RestExportComments restExportComments) {
        this.errorHandler = errorHandler;
        this.conversionService = conversionService;
        this.restExportComments = restExportComments;
    }

    @Override
    public Either<ErrorWrapper, AdminEditCommentOutputBff> process(AdminEditCommentInputBff input) {
        log.info("Start AdminEditComment input: {}", input);
        return   Try.of(()->{
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
                .mapLeft(errorHandler::handleError);
    }
}
