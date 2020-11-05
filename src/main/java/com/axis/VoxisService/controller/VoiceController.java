package com.axis.VoxisService.controller;

import com.axis.VoxisService.enums.RegisteredVoiceStatus;
import com.axis.VoxisService.enums.VoiceAuthenticateStatus;
import com.axis.VoxisService.exception.VoaxisException;
import com.axis.VoxisService.request.AuthenticateVoiceRequest;
import com.axis.VoxisService.request.RegisteredVoiceRequest;
import com.axis.VoxisService.response.ServiceResponse;
import com.axis.VoxisService.response.SpeechToTextResponse;
import com.axis.VoxisService.response.TextToSpeechResponse;
import com.axis.VoxisService.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/voxis/")
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @PostMapping(value = "v1/text-to-speech")
    public ServiceResponse<TextToSpeechResponse, VoaxisException> textToSpeech(
            @RequestParam @Valid final String text) {
        final TextToSpeechResponse textToSpeechResponse = voiceService.textToSpeech(text);
        ServiceResponse response = new ServiceResponse();
        response.setBody(textToSpeechResponse);
        return response;
    }

    @PostMapping(value = "v1/speech-to-text")
    public ServiceResponse<TextToSpeechResponse, VoaxisException> speechToText(
            @RequestParam @Valid final String base64EncodeSpeech) {
        final SpeechToTextResponse speechToText = voiceService.speechToText(base64EncodeSpeech);
        ServiceResponse response = new ServiceResponse();
        response.setBody(speechToText);
        return response;
    }

    @PostMapping(value = "v1/register-voice")
    public ServiceResponse<RegisteredVoiceStatus, VoaxisException> registeredVoice(
            @RequestBody @Valid final RegisteredVoiceRequest registeredVoiceRequest) {
        RegisteredVoiceStatus registeredVoiceStatus = voiceService.registeredVoice(registeredVoiceRequest);
        ServiceResponse response = new ServiceResponse();
        response.setBody(registeredVoiceStatus);
        return response;
    }

    @PostMapping(value = "v1/authenticate-voice")
    public ServiceResponse<VoiceAuthenticateStatus, VoaxisException> authenticateVoice(
            @RequestParam @Valid final AuthenticateVoiceRequest authenticateVoiceRequest) {
        VoiceAuthenticateStatus voiceAuthenticateStatus = voiceService.authenticateVoice(authenticateVoiceRequest);
        ServiceResponse response = new ServiceResponse();
        response.setBody(voiceAuthenticateStatus);
        return response;
    }

    @PostMapping(value = "v1/is-registered")
    public ServiceResponse<Boolean, VoaxisException> isRegistered(@RequestParam @Valid final String mobileNumber) {
        Boolean voiceRegisStatus = voiceService.isRegistered(mobileNumber);
        ServiceResponse response = new ServiceResponse();
        response.setBody(voiceRegisStatus);
        return response;
    }

    @PostMapping(value = "v1/generate-capta")
    public ServiceResponse<String, VoaxisException> generateCaptcha(
            @RequestParam @Valid final String mobileNumber) {
        String captcha = voiceService.generateCaptcha(mobileNumber);
        ServiceResponse response = new ServiceResponse();
        response.setBody(captcha);
        return response;
    }



}
