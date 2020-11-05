package com.axis.VoxisService.config;

import com.axis.VoxisService.service.CSVService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VoiceSamples {

    @Value("${csv.sample.file}")
    private String path;

    @Autowired
    private CSVService csvService;

    @Getter
    private Map<String,String> record = new HashMap<>();

    @PostConstruct
    public void init(){
        List<String[]> profileNumber = csvService.get(path);
        for(String[] i : profileNumber){
            record.put(i[0],i[1]);
        }
    }
}
