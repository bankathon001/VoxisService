package com.axis.VoxisService.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisteredVoiceRequest {

    @NotBlank
    @NotNull
    private String mobileNumber;
    @NotBlank
    @NotNull
    private String base64EncodeSpeech;
}
