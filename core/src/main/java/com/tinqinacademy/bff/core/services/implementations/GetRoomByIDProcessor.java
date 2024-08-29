package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.enums.BathRoomType;
import com.tinqinacademy.bff.api.models.hotel.enums.Bed;
import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIDInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIDOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIdOperation;
import com.tinqinacademy.bff.core.services.converters.hotelConverters.BathroomTypeBffToBathroomType;
import com.tinqinacademy.bff.core.services.converters.hotelConverters.BedBffToBed;
import com.tinqinacademy.hotel.api.models.operations.getRoomByID.GetRoomByIDInput;
import com.tinqinacademy.hotel.api.models.operations.getRoomByID.GetRoomByIDOutput;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import feign.FeignException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;


@Service
@Slf4j
public class GetRoomByIDProcessor implements GetRoomByIdOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;
    private final BathroomTypeBffToBathroomType bathroomTypeBffToBathroomType;
    private final BedBffToBed bedBffToBed;

    public GetRoomByIDProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService, BathroomTypeBffToBathroomType bathroomTypeBffToBathroomType, BedBffToBed bedBffToBed) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
        this.bathroomTypeBffToBathroomType = bathroomTypeBffToBathroomType;
        this.bedBffToBed = bedBffToBed;
    }

    @Override
    public Either<ErrorWrapper, GetRoomByIDOutputBff> process(GetRoomByIDInputBff input) {
        log.info("Start getRoomByID input: {}", input);
        return Try.of(()->{
                    GetRoomByIDInput converted = conversionService.convert(input, GetRoomByIDInput.class);
                    GetRoomByIDOutput roomsById = restExportHotel.getRoomsById(converted.getRoomID());
                    GetRoomByIDOutputBff output = outputBuilder(roomsById);
                    log.info("End getRoomById output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(FeignException.class)), errorHandler::handleFeignException),
                        Case($(), errorHandler::handleError)));
    }

    private GetRoomByIDOutputBff outputBuilder(GetRoomByIDOutput roomsById) {
        BathRoomType resultBathroom = bathroomTypeBffToBathroomType.convert(roomsById.getBathRoomType());
        List<Bed> bedList = bedBffToBed.convert(roomsById.getBed());
      return GetRoomByIDOutputBff.builder()
                .id(roomsById.getId())
                .bathRoomType(resultBathroom)
                .bed(bedList)
                .floor(roomsById.getFloor())
                .datesOccupied(roomsById.getDatesOccupied())
                .price(roomsById.getPrice())
                .build();
    }
}
