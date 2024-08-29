package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomOutputBff;
import com.tinqinacademy.hotel.api.models.operations.partialUpdateRoom.PartialUpdateRoomInput;
import com.tinqinacademy.hotel.api.models.operations.partialUpdateRoom.PartialUpdateRoomOutput;
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
public class PartialUpdateProcessor implements PartialUpdateRoomOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public PartialUpdateProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }


    @Override
    public Either<ErrorWrapper, PartialUpdateRoomOutputBff> process(PartialUpdateRoomInputBff input) {
        log.info("Start partialUpdateRoom input: {}", input);
        return Try.of(()->{
                    PartialUpdateRoomInput convert = conversionService.convert(input, PartialUpdateRoomInput.class);
                    PartialUpdateRoomOutput output = restExportHotel.partialUpdateRoom(convert, String.valueOf(convert.getId()));
                    PartialUpdateRoomOutputBff result = PartialUpdateRoomOutputBff.builder()
                            .id(output.getId())
                            .build();
                    log.info("End partialUpdateRoom output: {}", result);
                    return result;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));

    }
}
