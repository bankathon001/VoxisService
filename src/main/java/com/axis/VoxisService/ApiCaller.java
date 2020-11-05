package com.axis.VoxisService;

import com.axis.VoxisService.request.AuthenticateVoiceRequest;
import com.axis.VoxisService.request.RegisteredVoiceRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${azure.key}")
    private String speechSubscriptionKey;

    @Value("${azure.region}")
    private String serviceRegion;

    public boolean isRegistered(@NotBlank @NotNull final String mobileNumber){
        final String uri = "https://eastus.api.cognitive.microsoft.com/speaker/verification/v2.0/text-independent/profiles/"+mobileNumber;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Ocp-apim-subscription-key",speechSubscriptionKey);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, entity, JsonNode.class);
            if(response.getStatusCode() == HttpStatus.ACCEPTED){
                return true;
            }
        }catch (Exception e){
        }
        return false;
    }

    public JsonNode createProfile(@NotBlank @NotNull final String mobileNumber){
        final String uri = "https://eastus.api.cognitive.microsoft.com/speaker/verification/v2.0/text-independent/profiles";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Ocp-apim-subscription-key",speechSubscriptionKey);
        Map<String,String> body = new HashMap<>();
        body.put("locale","en-us");
        try {
            HttpEntity<Map> entity = new HttpEntity<Map>(body,headers);
            return restTemplate.postForObject(uri,entity, JsonNode.class);

        }catch (Exception e){
            return null;
        }
    }

    public JsonNode createEnrollment(final String profileId,final @NotNull RegisteredVoiceRequest registeredVoiceRequest){
        final String uri = "https://eastus.api.cognitive.microsoft.com/speaker/verification/v2.0/text-independent/profiles/"+profileId+"/enrollments?ignoreMinLength=true";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE,"audio/wav");
        headers.set("Ocp-apim-subscription-key",speechSubscriptionKey);
        Map<String,String> body = new HashMap<>();
        body.put("audioData",registeredVoiceRequest.getBase64EncodeSpeech());
        try {
            HttpEntity<Map> entity = new HttpEntity<Map>(body,headers);
            return restTemplate.postForObject(uri,entity, JsonNode.class);
        }catch (Exception e){
            return null;
        }
    }

    public JsonNode voiceAuthenticate(final String profileId,final @NotNull AuthenticateVoiceRequest authenticateVoiceRequest){
        final String uri = "https://eastus.api.cognitive.microsoft.com/speaker/verification/v2.0/text-independent/profiles/" + profileId + "/verify?ignoreMinLength=true";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE,"audio/wav");
        headers.set("Ocp-apim-subscription-key",speechSubscriptionKey);
        try {
            HttpEntity<String> entity = new HttpEntity<String>(authenticateVoiceRequest.getBase64EncodeSpeech(),headers);
            return restTemplate.postForObject(uri,entity, JsonNode.class);
        }catch (Exception e){
            return null;
        }
    }



}
