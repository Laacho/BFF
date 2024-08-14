package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor.RegisterVisitorInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor.RegisterVisitorOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor.RegisterVisitorOutputBff;
import com.tinqinacademy.hotel.api.models.operations.registerVisitor.RegisterVisitorInput;
import com.tinqinacademy.hotel.api.models.operations.registerVisitor.RegisterVisitorOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;


@Service
@Slf4j
public class RegisterVisitorProcessor implements RegisterVisitorOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public RegisterVisitorProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }

    @Override
    public Either<ErrorWrapper, RegisterVisitorOutputBff> process(RegisterVisitorInputBff input) {
        log.info("Start registerRoom input: {}", input);

        return Try.of(() -> {
                    RegisterVisitorInput convert = conversionService.convert(input, RegisterVisitorInput.class);
                    RegisterVisitorOutput registerVisitorOutput = restExportHotel.registerVisitor(convert);
                    RegisterVisitorOutputBff output = RegisterVisitorOutputBff.builder()
                            .message(registerVisitorOutput.getMessage())
                            .build();
                    log.info("End registerRoom output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(errorHandler::handleError);
    }
}
