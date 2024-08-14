package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomOutputBff;
import com.tinqinacademy.hotel.api.models.operations.unbookRoom.UnbookRoomInput;
import com.tinqinacademy.hotel.api.models.operations.unbookRoom.UnbookRoomOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnbookRoomProcessor implements UnbookRoomOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public UnbookRoomProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }

    @Override
    public Either<ErrorWrapper, UnbookRoomOutputBff> process(UnbookRoomInputBff input) {
        log.info("Start deleteRoom input: {}", input);
        return Try.of(() -> {
                    UnbookRoomInput convert = conversionService.convert(input, UnbookRoomInput.class);
                    UnbookRoomOutput unbookRoomOutput = restExportHotel.unbookRoom(convert.getBookingRoomId());
                    UnbookRoomOutputBff output = UnbookRoomOutputBff.builder()
                            .message(unbookRoomOutput.getMessage())
                            .build();
                    log.info("End deleteRoom output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(errorHandler::handleError);
    }
}
