package com.innocito.apitestingplatform.apiresponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseMessage<T> {
    private String status;
    private String message;
    private T data;

    public ApiResponseMessage(int value, String message, long l) {
    }
}