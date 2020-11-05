package com.axis.VoxisService.service;

import com.axis.VoxisService.ApiCaller;
import com.axis.VoxisService.enums.RegisteredVoiceStatus;
import com.axis.VoxisService.enums.VoiceAuthenticateStatus;
import com.axis.VoxisService.request.AuthenticateVoiceRequest;
import com.axis.VoxisService.request.RegisteredVoiceRequest;
import com.axis.VoxisService.response.SpeechToTextResponse;
import com.axis.VoxisService.response.TextToSpeechResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;

@Service
public class VoiceService {

    @Value("${azure.key}")
    private String speechSubscriptionKey;

    @Value("${azure.region}")
    private String serviceRegion;

    @Autowired
    private ApiCaller apiCaller;

    private final Map<String,String> mobileProfileMap = new HashMap<>();

    public TextToSpeechResponse textToSpeech(@NotNull @NotBlank String text) {
        TextToSpeechResponse textToSpeechResponse = new TextToSpeechResponse();
        try {
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            SpeechSynthesizer synth = new SpeechSynthesizer(config);
            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);
            SpeechSynthesisResult result = task.get();
            byte[] audioData = result.getAudioData();
            textToSpeechResponse.setBase64EncodeSpeech(Base64.getEncoder().encodeToString(audioData));
        } catch (Exception e) {
        }
        return textToSpeechResponse;
    }

    public SpeechToTextResponse speechToText(@NotNull @NotBlank String base64EncodeSpeech) {
        return null;
    }

    public RegisteredVoiceStatus registeredVoice(@NotNull RegisteredVoiceRequest registeredVoiceRequest) {
        String profileId = null;
        profileId = mobileProfileMap.get(registeredVoiceRequest.getMobileNumber());
        if(StringUtils.isEmpty(profileId)) {
            JsonNode jsonNode = apiCaller.createProfile(registeredVoiceRequest.getMobileNumber());
            profileId = jsonNode.get("profileId").toString();
            mobileProfileMap.put(registeredVoiceRequest.getMobileNumber(),profileId);
        }
        apiCaller.createEnrollment(profileId);
        return null;
    }

    public VoiceAuthenticateStatus authenticateVoice(AuthenticateVoiceRequest authenticateVoiceRequest) {
        return null;
    }

    public boolean isRegistered(@NotNull @NotBlank final String mobileNumber) {
        return apiCaller.isRegistered(mobileNumber);
    }

    public String generateCaptcha(@NotNull @NotBlank final String mobileNumber) {
        String[] a = new String[]{
                "What a big dog that is",
                "Do not open the door",
                "Does he play tennis",
                "What are you doing",
                "I trust you so much.",
                "Do not worry be happy",
                "What a big dog",
                "Mind your own business",
                "Do not open the door",
                "Hand me the hammer"};
        Random random = new Random();
        return a[random.nextInt(a.length)];
    }
}
