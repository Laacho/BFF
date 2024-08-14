package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomOutputBff;
import com.tinqinacademy.hotel.api.models.operations.updateRoom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.models.operations.updateRoom.UpdateRoomOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateRoomProcessor implements UpdateRoomOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public UpdateRoomProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }

    @Override
    public Either<ErrorWrapper, UpdateRoomOutputBff> process(UpdateRoomInputBff input) {
        log.info("Start updateRoom input: {}", input);
        return Try.of(()->{
                    UpdateRoomInput convert = conversionService.convert(input, UpdateRoomInput.class);
                    UpdateRoomOutput updateRoomOutput = restExportHotel.updateRoom(convert, convert.getId());
                    UpdateRoomOutputBff output = UpdateRoomOutputBff.builder()
                            .id(updateRoomOutput.getId())
                            .build();
                    log.info("End updateRoom output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(errorHandler::handleError);


    }
}
