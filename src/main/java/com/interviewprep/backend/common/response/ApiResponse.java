package com.interviewprep.backend.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.interviewprep.backend.common.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private int code;
    private boolean success;
    private String message;
    private Object data;

    public static class ApiResponseBuilder {

        public ApiResponse build() {
            if (this.message == null) {
                this.message = Constants.RESPONSE.get(this.code);
            }
            if (this.data == null) {
                this.data = Collections.emptyMap();
            }
            return new ApiResponse(this.code, this.success, this.message, this.data);
        }
    }
}