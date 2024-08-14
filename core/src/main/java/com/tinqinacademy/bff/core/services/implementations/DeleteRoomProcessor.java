package com.tinqinacademy.bff.core.services.implementations;


import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomOutputBff;
import com.tinqinacademy.hotel.api.models.operations.deleteRoom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.models.operations.deleteRoom.DeleteRoomOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteRoomProcessor implements DeleteRoomOperation {

    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;

    public DeleteRoomProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
    }

    @Override
    public Either<ErrorWrapper, DeleteRoomOutputBff> process(DeleteRoomInputBff input) {
        log.info("Start deleteRoom input: {}", input);
        return Try.of(()->{
                    DeleteRoomInput converted = conversionService.convert(input, DeleteRoomInput.class);
                    DeleteRoomOutput output = restExportHotel.deleteRoom(converted.getRoomId());
                    DeleteRoomOutputBff outputBff = DeleteRoomOutputBff.builder()
                            .message(output.getMessage())
                            .build();
                    log.info("End deleteRoom output: {}", outputBff);
                    return outputBff;

                })
                .toEither()
                .mapLeft(errorHandler::handleError);
    }
}
