package com.tinqinacademy.bff.api.models.exceptions.errorWrapper;

import com.tinqinacademy.bff.api.models.exceptions.baseError.Error;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorWrapper {
    private List<Error> errors;
    public void addErrors(Error error){
        this.errors.add(error);
    }


}