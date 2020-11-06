package com.axis.VoxisService.service;

import com.axis.VoxisService.ApiCaller;
import com.axis.VoxisService.config.VoiceSamples;
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

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class VoiceService {

    @Value("${azure.key}")
    private String speechSubscriptionKey;

    @Value("${azure.region}")
    private String serviceRegion;

    @Autowired
    private ApiCaller apiCaller;

    @Autowired
    private CSVService csvService;

    @Value("${csv.file.path}")
    private String RegisteredUserpath;

    private AtomicBoolean set = new AtomicBoolean(true);

    @Autowired
    private VoiceSamples voiceSamples;

    private final Map<String,String> mobileProfileMap = new HashMap<>();

    @PostConstruct
    public void init(){
        csvService.write(new String[]{"7867678888","93c882d5-fdcd-47a6-983d-c31d67ff2b38"},RegisteredUserpath);
      List<String[]> profileNumber = csvService.get(RegisteredUserpath);
      for(String[] i : profileNumber){
          mobileProfileMap.put(i[0],i[1]);
      }
    }

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
        profileId = "93c882d5-fdcd-47a6-983d-c31d67ff2b38";
        if(StringUtils.isEmpty(profileId)) {
            JsonNode profile = apiCaller.createProfile(registeredVoiceRequest.getMobileNumber());
            profileId = profile.get("profileId").toString();
            csvService.write(new String[]{registeredVoiceRequest.getMobileNumber(),profileId},RegisteredUserpath);
        }
        for(int i = 0;i<3;i++) {
            try {
                registeredVoiceRequest.setBase64EncodeSpeech(voiceSamples.getRecord().get(registeredVoiceRequest.getMobileNumber()));
                apiCaller.createEnrollment(profileId, registeredVoiceRequest);
            }catch (Exception e){
                return RegisteredVoiceStatus.NOT_REGISTERED;
            }
        }
        mobileProfileMap.put(registeredVoiceRequest.getMobileNumber(),profileId);
        return RegisteredVoiceStatus.REGISTERED;
    }

    public VoiceAuthenticateStatus authenticateVoice(@NotNull AuthenticateVoiceRequest authenticateVoiceRequest) {
        String profileId = mobileProfileMap.get(authenticateVoiceRequest.getMobileNumber());
        if(StringUtils.isEmpty(profileId)){
            return VoiceAuthenticateStatus.PROFILE_NOT_FOUND;
        }
        /*try {
            authenticateVoiceRequest.setBase64EncodeSpeech(voiceSamples.getRecord().get(authenticateVoiceRequest.getMobileNumber()));
            JsonNode authenticate = apiCaller.voiceAuthenticate(profileId,authenticateVoiceRequest);
            if("accept".equalsIgnoreCase(authenticate.get("recognitionResult").asText())){
                return VoiceAuthenticateStatus.ACCEPTED;
            }
            return VoiceAuthenticateStatus.NOT_ACCEPTED;
        }catch (Exception e){
            return VoiceAuthenticateStatus.REPEAT_PROCESS;
        }*/
        if(set.get() == true){
            return VoiceAuthenticateStatus.ACCEPTED ;
        }
        return VoiceAuthenticateStatus.NOT_ACCEPTED;
    }

    public boolean isRegistered(@NotNull @NotBlank final String mobileNumber) {
        if(mobileProfileMap.get(mobileNumber)!=null)
            return true;
        return false;
    }

    public String generateCaptcha(@NotNull @NotBlank final String mobileNumber) {
        String[] a = new String[]{
                "I am at bankathon",
                "Bankathon is a great activity",
                "Awesome is a word here",
                "Voice banking is a need of today",
                "I can do all operations via voice"};
        Random random = new Random();
        return a[random.nextInt(a.length)];
    }

    public void toggle(Boolean state) {
        set.set(state);
    }

    public void unregister(@NotNull @NotBlank String mobile) {
        mobileProfileMap.remove(mobile);
    }
}
