package com.axis.VoxisService.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The Exception at the top of the hierarchy for the project. It extends
 * RunTimeException
 */
@Getter
@JsonIgnoreProperties({"stackTrace","localizedMessage","suppressed","cause","message"})
@NoArgsConstructor
public class VoaxisException extends RuntimeException{

    private static final long serialVersionUID = 750502363542167647L;

    private boolean retryable;
    private String errorCode;
    private String errorMessage;

    public VoaxisException(ErrorCodeAndMessage errorCodeAndMessage, String message) {
        super(message);
        this.errorCode = errorCodeAndMessage.getErrorCode();
        this.errorMessage = message;
        this.retryable = errorCodeAndMessage.isRetryable();
    }

    public VoaxisException(ErrorCodeAndMessage errorCodeAndMessage) {
        super(errorCodeAndMessage.getMessage());
        this.errorCode = errorCodeAndMessage.getErrorCode();
        this.errorMessage = errorCodeAndMessage.getMessage();
        this.retryable = errorCodeAndMessage.isRetryable();
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    public boolean isRetryable() {
        return this.retryable;
    }
}
