package com.tinqinacademy.bff.rest;

import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomOutputBff;
import com.tinqinacademy.hotel.core.services.paths.URLPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HotelController extends BaseController {
    private final BookRoomOperation bookRoomOperation;


    public HotelController(BookRoomOperation bookRoomOperation) {
        this.bookRoomOperation = bookRoomOperation;
    }


    @PostMapping(URLPaths.ROOM_ID)
    @Operation(summary = "books a rooms DONE")
    public ResponseEntity<?> bookRoom(@RequestBody BookRoomInputBff input,
                                      @PathVariable UUID roomId) {

        Either<ErrorWrapper, BookRoomOutputBff> process = bookRoomOperation.process(input);
        return handleResponse(process);
    }

}
