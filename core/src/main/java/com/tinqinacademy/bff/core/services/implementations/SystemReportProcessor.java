package com.tinqinacademy.bff.core.services.implementations;

import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.Data;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemReportInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemReportOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemRepostOutputBff;
import com.tinqinacademy.bff.core.services.converters.hotelConverters.DataToDataBff;
import com.tinqinacademy.hotel.api.models.operations.systemRepost.SystemReportInput;
import com.tinqinacademy.hotel.api.models.operations.systemRepost.SystemRepostOutput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import com.tinqinacademy.hotel.restexport.RestExportHotel;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.tinqinacademy.bff.api.models.exceptions.errorHandler.ErrorHandler;

import java.util.List;


@Service
@Slf4j
public class SystemReportProcessor implements SystemReportOperation {
    private final ErrorHandler errorHandler;
    private final RestExportHotel restExportHotel;
    private final ConversionService conversionService;
    private final DataToDataBff dataToDataBff;

    public SystemReportProcessor(ErrorHandler errorHandler, RestExportHotel restExportHotel, ConversionService conversionService, DataToDataBff dataToDataBff) {
        this.errorHandler = errorHandler;
        this.restExportHotel = restExportHotel;
        this.conversionService = conversionService;
        this.dataToDataBff = dataToDataBff;

    }

    @Override
    public Either<ErrorWrapper, SystemRepostOutputBff> process(SystemReportInputBff input) {
        log.info("Start systemReportOperation input: {}", input);
        return Try.of(() -> {
                    SystemReportInput convert = conversionService.convert(input, SystemReportInput.class);
                    SystemRepostOutput systemRepostOutput = restExportHotel.reportInfo(
                            convert.getStartDate(),
                            convert.getEndDate(),
                            convert.getRoomNumber(),
                            convert.getFirstName(),
                            convert.getLastName(),
                            convert.getPhoneNumber(),
                            convert.getIdCardNumber(),
                            convert.getIdCardValidity(),
                            convert.getIdCardIssueAuthority(),
                            convert.getIdCardIssueDate()
                    );
                    List<Data> convertedData = dataToDataBff.convert(systemRepostOutput.getData());
                    SystemRepostOutputBff output = SystemRepostOutputBff
                            .builder()
                            .data(convertedData)
                            .startDate(systemRepostOutput.getStartDate())
                            .endDate(systemRepostOutput.getEndDate())
                            .build();
                    log.info("End getRegisterInfo output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(errorHandler::handleError);
    }
}
