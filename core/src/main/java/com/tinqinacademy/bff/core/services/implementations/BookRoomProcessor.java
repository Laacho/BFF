package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomOutputBff;
import com.tinqinacademy.hotel.api.models.operations.bookRoom.BookRoomInput;
import com.tinqinacademy.hotel.api.models.operations.bookRoom.BookRoomOutput;
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
public class BookRoomProcessor implements BookRoomOperation {
    private final RestExportHotel restExportHotel;
    private final ErrorHandler errorHandler;
    private final ConversionService conversionService;

    public BookRoomProcessor(RestExportHotel restExportHotel, ErrorHandler errorHandler, ConversionService conversionService) {
        this.restExportHotel = restExportHotel;
        this.errorHandler = errorHandler;
        this.conversionService = conversionService;
    }


    @Override
    public Either<ErrorWrapper, BookRoomOutputBff> process(BookRoomInputBff input) {
        log.info("Start bookRoom input: {}", input);
        return   Try.of(()->{
                    BookRoomInput result = conversionService.convert(input, BookRoomInput.class);
                    BookRoomOutput output = restExportHotel.bookRoom(result, input.getRoomId());
                    log.info("End bookRoom output: {}", output);
                    BookRoomOutputBff outputBff = BookRoomOutputBff.builder()
                            .message(output.getMessage())
                            .build();
                    log.info("End bookRoom output: {}", outputBff);
                    return outputBff;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }
}
