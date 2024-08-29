package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsOutputBff;
import com.tinqinacademy.hotel.api.models.operations.getFreeRooms.GetFreeRoomsInput;
import com.tinqinacademy.hotel.api.models.operations.getFreeRooms.GetFreeRoomsOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
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
public class GetFreeRoomsProcessor implements GetFreeRoomsOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public GetFreeRoomsProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }

    @Override
    public Either<ErrorWrapper, GetFreeRoomsOutputBff> process(GetFreeRoomsInputBff input) {
        log.info("Start getFreeRooms input: {}", input);
        return Try.of(() -> {
                    GetFreeRoomsInput converted = conversionService.convert(input, GetFreeRoomsInput.class);
                    GetFreeRoomsOutput output = restExportHotel.checkIfRoomIsFree(converted.getStartDate()
                            , converted.getEndDate()
                            , converted.getBeds()
                            , converted.getBathRoomType()
                    );
                    GetFreeRoomsOutputBff outputBff = GetFreeRoomsOutputBff.builder()
                            .ids(output.getIds())
                            .build();
                    log.info("End getRoomById output: {}", outputBff);
                    return outputBff;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }
}
