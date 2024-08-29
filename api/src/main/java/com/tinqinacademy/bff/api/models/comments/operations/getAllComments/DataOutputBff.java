package com.tinqinacademy.bff.api.models.comments.operations.getAllComments;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataOutputBff {
    private String id;
    private String firstName;
    private String lastName;
    private String content;
    private LocalDate publishedDate;
    private LocalDate lastEditedDate;
    private String lastEditedBy;
}
