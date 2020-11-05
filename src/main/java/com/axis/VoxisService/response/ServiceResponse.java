package com.axis.VoxisService.response;

import com.axis.VoxisService.exception.VoaxisException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Objects;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<T,R extends VoaxisException> {
    private R exception;
    private T body;
    private boolean success;
    private long serverTimeStamp;

    public ServiceResponse(){
       this.serverTimeStamp = System.currentTimeMillis();
       this.body = null;
       this.exception = null;
    }

    public void setException(R exception) {
        this.exception = exception;
    }

    public void setBody(T body) {
        this.body = body;
        this.success = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exception, body, success, serverTimeStamp);
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "exception=" + exception +
                ", body=" + body +
                ", success=" + success +
                ", serverTimeStamp=" + serverTimeStamp +
                '}';
    }
}
