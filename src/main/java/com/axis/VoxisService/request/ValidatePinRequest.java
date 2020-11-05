package com.axis.VoxisService.request;

import lombok.Data;

@Data
public class ValidatePinRequest {

    private String mobileNumber;
    private String debitPin;
}
