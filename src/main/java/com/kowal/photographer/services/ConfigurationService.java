package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Configuration;
import com.kowal.photographer.repositorys.ConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class ConfigurationService {
    private final ConfigRepository configRepository;
    Map<String,String> colorMap;

    public ConfigurationService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        colorMap = new HashMap<>();
        colorMap.put("red","is-danger");
        colorMap.put("yellow","is-warning");
        colorMap.put("green","is-success");
        colorMap.put("blue","is-info");
        colorMap.put("darkBlue","is-link");
        colorMap.put("darkGreen","is-primary");
    }

    public void checkConfigurationIfEmptyCreate(){
        getMaxPerDay();
        getSiteColor();

    }

    private Configuration getMaxPerDay(){
        Configuration maxPerDay = configRepository.getMaxPerDay();
        if( maxPerDay == null){
            Configuration c = new Configuration();
            c.setName("max_per_day");
            c.setValue("3");
            configRepository.save(c);
        }
        return maxPerDay;
    }

    public Integer getIntegerMaxPerDay(){
        return Integer.parseInt(getMaxPerDay().getValue());
    }

    private Configuration getSiteColor(){
        Configuration siteColor = configRepository.getSiteColor();
        if( siteColor == null){
            Configuration c = new Configuration();
            c.setName("site_color");
            c.setValue(colorMap.get("blue"));
            configRepository.save(c);
        }
        return siteColor;
    }

    public String getStringSiteColor(){
        return getSiteColor().getValue();
    }

    public void setMaxPerDay(String maxPerDay){
        Configuration c = getMaxPerDay();
        c.setValue(maxPerDay);
        configRepository.save(c);
    }

    public void setSiteColor(String color){
        Configuration c = getSiteColor();
        String colorFromMap = colorMap.get(color);
        if( colorFromMap == null){
            log.error("{} isn't correct color", color);
        } else {
            c.setValue(colorFromMap);
            configRepository.save(c);
        }
    }


}
