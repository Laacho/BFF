package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomOutputBff;
import com.tinqinacademy.hotel.api.models.operations.createRoom.CreateRoomInput;
import com.tinqinacademy.hotel.api.models.operations.createRoom.CreateRoomOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CreateRoomProcessor implements CreateRoomOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public CreateRoomProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }

    @Override
    public Either<ErrorWrapper, CreateRoomOutputBff> process(CreateRoomInputBff input) {
        log.info("Start createRoom input: {}", input);
        return Try.of(()->{
                    CreateRoomInput converted = conversionService.convert(input, CreateRoomInput.class);
                    CreateRoomOutput output = restExportHotel.createRoom(converted);
                    CreateRoomOutputBff outputBff = CreateRoomOutputBff.builder()
                            .id(output.getId())
                            .build();
                    log.info("End createRoom output: {}", outputBff);
                    return outputBff;


                })
                .toEither()
                .mapLeft(errorHandler::handleError);
    }
}
