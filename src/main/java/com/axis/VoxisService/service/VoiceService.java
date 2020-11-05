package com.axis.VoxisService.service;

import com.axis.VoxisService.enums.RegisteredVoiceStatus;
import com.axis.VoxisService.enums.VoiceAuthenticateStatus;
import com.axis.VoxisService.request.AuthenticateVoiceRequest;
import com.axis.VoxisService.request.RegisteredVoiceRequest;
import com.axis.VoxisService.response.SpeechToTextResponse;
import com.axis.VoxisService.response.TextToSpeechResponse;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Service
public class VoiceService {

    public TextToSpeechResponse textToSpeech(@NotNull @NotBlank String text) {
        return null;
    }

    public SpeechToTextResponse speechToText(@NotNull @NotBlank String base64EncodeSpeech) {
        return null;
    }

    public RegisteredVoiceStatus registeredVoice(@NotNull RegisteredVoiceRequest registeredVoiceRequest) {
        return null;
    }

    public VoiceAuthenticateStatus authenticateVoice(AuthenticateVoiceRequest authenticateVoiceRequest) {
        return null;
    }
}
