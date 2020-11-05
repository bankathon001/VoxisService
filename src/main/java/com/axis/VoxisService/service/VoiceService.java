package com.axis.VoxisService.service;

import com.axis.VoxisService.enums.RegisteredVoiceStatus;
import com.axis.VoxisService.enums.VoiceAuthenticateStatus;
import com.axis.VoxisService.request.AuthenticateVoiceRequest;
import com.axis.VoxisService.request.RegisteredVoiceRequest;
import com.axis.VoxisService.response.SpeechToTextResponse;
import com.axis.VoxisService.response.TextToSpeechResponse;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.Future;

@Service
public class VoiceService {

    @Value("${azure.key}")
    private String speechSubscriptionKey;

    @Value("${azure.region}")
    private String serviceRegion;

    public TextToSpeechResponse textToSpeech(@NotNull @NotBlank String text) {
        try {
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            SpeechSynthesizer synth = new SpeechSynthesizer(config);
            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);
            SpeechSynthesisResult result = task.get();
        } catch (Exception e) {
        }
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
