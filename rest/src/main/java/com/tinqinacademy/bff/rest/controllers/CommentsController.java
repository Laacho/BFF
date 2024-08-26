package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.adminEditComment.AdminEditCommentOutputBff;
import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.deleteComment.DeleteCommentOutputBff;
import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentOperation;
import com.tinqinacademy.bff.api.models.comments.operations.editComment.EditCommentOutputBff;
import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsOperation;
import com.tinqinacademy.bff.api.models.comments.operations.getAllComments.GetAllCommentsOutputBff;
import com.tinqinacademy.bff.api.models.comments.operations.postComment.PostCommentInputBff;
import com.tinqinacademy.bff.api.models.comments.operations.postComment.PostCommentOperation;
import com.tinqinacademy.bff.api.models.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.core.services.paths.CommentsURLPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CommentsController extends BaseController {

    private final GetAllCommentsOperation getAllCommentsOperation;
    private final PostCommentOperation postCommentOperation;
    private final EditCommentOperation editCommentOperation;
    private final AdminEditCommentOperation adminEditCommentOperation;
    private final DeleteCommentOperation deleteCommentOperation;

    public CommentsController(GetAllCommentsOperation getAllCommentsOperation, PostCommentOperation postCommentOperation, EditCommentOperation editCommentOperation, AdminEditCommentOperation adminEditCommentOperation, DeleteCommentOperation deleteCommentOperation) {
        this.getAllCommentsOperation = getAllCommentsOperation;
        this.postCommentOperation = postCommentOperation;
        this.editCommentOperation = editCommentOperation;
        this.adminEditCommentOperation = adminEditCommentOperation;
        this.deleteCommentOperation = deleteCommentOperation;
    }


    @GetMapping(CommentsURLPaths.GET_ROOM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "get All Comments ")
    public ResponseEntity<?> getAllComments(@PathVariable String roomID){
        GetAllCommentsInputBff input = GetAllCommentsInputBff.builder()
                .roomId(roomID)
                .build();
        Either<ErrorWrapper, GetAllCommentsOutputBff> process = getAllCommentsOperation.process(input);
        return handleResponse(process);
    }

    @PostMapping(CommentsURLPaths.POST_ROOM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "post comment")
    public ResponseEntity<?> postComments(@PathVariable String roomID,
                                          @RequestBody PostCommentInput input) {
        PostCommentInputBff result = PostCommentInputBff.builder()
                .roomId(roomID)
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .content(input.getContent())
                .build();
        return handleResponse(postCommentOperation.process(result));
    }

    @PatchMapping(value = CommentsURLPaths.PATCH_HOTEL_COMMENT, consumes = "application/json-patch+json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "partial update of a comment")
    public ResponseEntity<?> editComment(@PathVariable String commentId,@RequestBody String content){
        EditCommentInputBff input = EditCommentInputBff.builder()
                .content(content)
                .id(UUID.fromString(commentId))
                .build();
        Either<ErrorWrapper, EditCommentOutputBff> process = editCommentOperation.process(input);
        return handleResponse(process);
    }


    @PutMapping(CommentsURLPaths.PUT_SYSTEM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "admin edits comments")
    public ResponseEntity<?> adminEditComment(@PathVariable String commentId,  @RequestBody AdminEditCommentInput input){
        AdminEditCommentInputBff inputFinal = AdminEditCommentInputBff.builder()
                .commentId(commentId)
                .content(input.getContent())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .roomNumber(input.getRoomNumber())
                .build();
        Either<ErrorWrapper, AdminEditCommentOutputBff> result = adminEditCommentOperation.process(inputFinal);
        return handleResponse(result);
    }

    @DeleteMapping(CommentsURLPaths.DELETE_SYSTEM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "Deletes a comment")
    public ResponseEntity<?> deleteComment(@PathVariable String commentId){
        DeleteCommentInputBff input = DeleteCommentInputBff.builder()
                .commentId(commentId)
                .build();
        Either<ErrorWrapper, DeleteCommentOutputBff> process = deleteCommentOperation.process(input);
        return  handleResponse(process);

    }
}


