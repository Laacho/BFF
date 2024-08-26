package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.bookRoom.BookRoomOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.createRoom.CreateRoomOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.deleteRoom.DeleteRoomOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.getFreeRooms.GetFreeRoomsOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIDInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIDOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.getRoomByID.GetRoomByIdOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.partialUpdateRoom.PartialUpdateRoomOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor.RegisterVisitorInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor.RegisterVisitorOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.registerVisitor.RegisterVisitorOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemReportInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemReportOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.systemRepost.SystemRepostOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.unbookRoom.UnbookRoomOutputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomInputBff;
import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomOperation;
import com.tinqinacademy.bff.api.models.hotel.operations.updateRoom.UpdateRoomOutputBff;
import com.tinqinacademy.hotel.core.services.paths.HotelURLPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class HotelController extends BaseController {
    private final BookRoomOperation bookRoomOperation;
    private final GetFreeRoomsOperation getFreeRoomsOperation;
    private final GetRoomByIdOperation getRoomByIdOperation;
    private final UnbookRoomOperation unbookRoomOperation;
    private final RegisterVisitorOperation registerVisitorOperation;
    private final SystemReportOperation systemReportOperation;
    private final CreateRoomOperation createRoomOperation;
    private final UpdateRoomOperation updateRoomOperation;
    private final PartialUpdateRoomOperation partialUpdateRoomOperation;
    private final DeleteRoomOperation deleteRoomOperation;


    public HotelController(BookRoomOperation bookRoomOperation, GetFreeRoomsOperation getFreeRoomsOperation, GetRoomByIdOperation getRoomByIdOperation, UnbookRoomOperation unbookRoomOperation, RegisterVisitorOperation registerVisitorOperation, SystemReportOperation systemReportOperation, CreateRoomOperation createRoomOperation, UpdateRoomOperation updateRoomOperation, PartialUpdateRoomOperation partialUpdateRoomOperation, DeleteRoomOperation deleteRoomOperation) {
        this.bookRoomOperation = bookRoomOperation;
        this.getFreeRoomsOperation = getFreeRoomsOperation;
        this.getRoomByIdOperation = getRoomByIdOperation;
        this.unbookRoomOperation = unbookRoomOperation;
        this.registerVisitorOperation = registerVisitorOperation;
        this.systemReportOperation = systemReportOperation;
        this.createRoomOperation = createRoomOperation;
        this.updateRoomOperation = updateRoomOperation;
        this.partialUpdateRoomOperation = partialUpdateRoomOperation;
        this.deleteRoomOperation = deleteRoomOperation;
    }

    //hotel
    @PostMapping(HotelURLPaths.POST_ROOM_ID)
    @Operation(summary = "books a rooms DONE")
    public ResponseEntity<?> bookRoom(@RequestBody BookRoomInputBff input,
                                      @PathVariable UUID roomId) {
        BookRoomInputBff.builder()
                .roomId(roomId)
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .birthdate(input.getBirthdate())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .build();
        Either<ErrorWrapper, BookRoomOutputBff> process = bookRoomOperation.process(input);
        return handleResponse(process);
    }

    @GetMapping(HotelURLPaths.GET_ROOMS)
    @Operation(summary = "checks if a room is free DONE")
    public ResponseEntity<?> checkIfRoomIsFree(@RequestParam LocalDate startDate,
                                               @RequestParam LocalDate endDate,
                                               @RequestParam List<String> beds,
                                               @RequestParam String bathRoomType
    ) {
        GetFreeRoomsInputBff input = GetFreeRoomsInputBff.builder()
                .bathRoomType(bathRoomType)
                .startDate(startDate)
                .endDate(endDate)
                .beds(beds)
                .build();
        Either<ErrorWrapper, GetFreeRoomsOutputBff> process = getFreeRoomsOperation.process(input);
        return handleResponse(process);
    }

    @GetMapping(HotelURLPaths.GET_ROOM_ID)
    @Operation(summary = "returns basic info for a room DONE")
    public ResponseEntity<?> getRoomsById(@PathVariable UUID roomId) {
        GetRoomByIDInputBff input = GetRoomByIDInputBff.builder()
                .roomID(roomId)
                .build();
        Either<ErrorWrapper, GetRoomByIDOutputBff> result = getRoomByIdOperation.process(input);
        return handleResponse(result);
    }

    @DeleteMapping(HotelURLPaths.DELETE_BOOKING_ID)
    @Operation(summary = "unbooks a room DONE")
    public ResponseEntity<?> unbookRoom(@PathVariable("bookingId") UUID bookingId) {
        UnbookRoomInputBff build = UnbookRoomInputBff.builder()
                .bookingRoomId(bookingId)
                .build();
        Either<ErrorWrapper, UnbookRoomOutputBff> process = unbookRoomOperation.process(build);
        return handleResponse(process);
    }

    //system

    @PostMapping(HotelURLPaths.POST_REGISTER)
    @Operation(summary = "registers a visitor DONE")
    public ResponseEntity<?> registerVisitor(@RequestBody RegisterVisitorInputBff input) {
        Either<ErrorWrapper, RegisterVisitorOutputBff> process = registerVisitorOperation.process(input);
        return handleResponse(process);
    }


    @GetMapping(HotelURLPaths.GET_REGISTER)
    @Operation(summary = "gives a report on a specific info")
    public ResponseEntity<?> reportInfo(@RequestParam LocalDate startDate,
                                        @RequestParam LocalDate endDate,
                                        @RequestParam String roomNumber,
                                        @RequestParam(required = false) String firstName,
                                        @RequestParam(required = false) String lastName,
                                        @RequestParam(required = false) String phoneNumber,
                                        @RequestParam(required = false) String idCardNumber,
                                        @RequestParam(required = false) String idCardValidity,
                                        @RequestParam(required = false) String idCardIssueAuthority,
                                        @RequestParam(required = false) String idCardIssueDate) {
        SystemReportInputBff input = SystemReportInputBff.builder()
                .startDate(startDate)
                .endDate(endDate)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .idCardNumber(idCardNumber)
                .idCardValidity(idCardValidity)
                .idCardIssueAuthority(idCardIssueAuthority)
                .idCardIssueDate(idCardIssueDate)
                .roomNumber(roomNumber)
                .build();
        Either<ErrorWrapper, SystemRepostOutputBff> result = systemReportOperation.process(input);
        return handleResponse(result);
    }

    @PostMapping(HotelURLPaths.POST_SYSTEM_ROOM)
    @Operation(summary = "Adds a room DONE")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomInputBff input) {
        Either<ErrorWrapper, CreateRoomOutputBff> result = createRoomOperation.process(input);
        return handleResponse(result);
    }

    @PutMapping(HotelURLPaths.PUT_SYSTEM_ROOM_ID)
    @Operation(summary = "updates room DONE")
    public ResponseEntity<?> updateRoom(@RequestBody UpdateRoomInputBff input, @PathVariable UUID roomId) {
        UpdateRoomInputBff result = UpdateRoomInputBff.builder()
                .id(roomId)
                .bathroomType(input.getBathroomType())
                .floor(input.getFloor())
                .roomNumber(input.getRoomNumber())
                .price(input.getPrice())
                .beds(input.getBeds())
                .build();
        Either<ErrorWrapper, UpdateRoomOutputBff> process = updateRoomOperation.process(result);
        return handleResponse(process);
    }

    @PatchMapping(value = HotelURLPaths.PATCH_SYSTEM_ROOM_ID, consumes = "application/json-patch+json")
    @Operation(summary = "partial updated the room DONE")
    public ResponseEntity<?> partialUpdateRoom(@RequestBody PartialUpdateRoomInputBff input,
                                               @PathVariable String roomId) {
        PartialUpdateRoomInputBff result = PartialUpdateRoomInputBff.builder()
                .id(UUID.fromString(roomId))
                .bathroomType(input.getBathroomType())
                .floor(input.getFloor())
                .roomNumber(input.getRoomNumber())
                .price(input.getPrice())
                .beds(input.getBeds())
                .build();
        Either<ErrorWrapper, PartialUpdateRoomOutputBff> process = partialUpdateRoomOperation.process(result);
        return handleResponse(process);
    }

    @DeleteMapping(HotelURLPaths.DELETE_SYSTEM_ROOM_ID)
    @Operation(summary = "deletes a room DONE")
    public ResponseEntity<?> deleteRoom( @PathVariable UUID roomId) {
        DeleteRoomInputBff input = DeleteRoomInputBff.builder()
                .roomId(roomId)
                .build();
        Either<ErrorWrapper, DeleteRoomOutputBff> process = deleteRoomOperation.process(input);
        return handleResponse(process);
    }
}
