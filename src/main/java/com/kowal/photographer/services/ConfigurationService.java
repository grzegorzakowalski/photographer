package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Configuration;
import com.kowal.photographer.repositorys.ConfigRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class ConfigurationService {
    private final ConfigRepository configRepository;
    @Getter
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
        getAboutMe();
        getContactPhoneNumber();
    }

    private Configuration getMaxPerDay(){
        Configuration maxPerDay = configRepository.getDistinctByNameIsLike("max_per_day");
        if( maxPerDay == null){
            maxPerDay = new Configuration();
            maxPerDay.setName("max_per_day");
            maxPerDay.setValue("3");
            configRepository.save(maxPerDay);
        }
        return maxPerDay;
    }

    public Integer getIntegerMaxPerDay(){
        return Integer.parseInt(getMaxPerDay().getValue());
    }

    private Configuration getSiteColor(){
        Configuration siteColor = configRepository.getDistinctByNameIsLike("site_color");
        if( siteColor == null){
            siteColor = new Configuration();
            siteColor.setName("site_color");
            siteColor.setValue(colorMap.get("blue"));
            configRepository.save(siteColor);
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

    private Configuration getAboutMe(){
        Configuration aboutMe = configRepository.getDistinctByNameIsLike("about_me");
        if( aboutMe == null){
            aboutMe = new Configuration();
            aboutMe.setName("about_me");
            aboutMe.setValue("Tutaj coś do powiedzenia o sobie");
            configRepository.save(aboutMe);
        }
        return aboutMe;
    }

    public String getStringAboutMe(){
        return getAboutMe().getValue();
    }

    public void setAboutMe(String inputText){
        Configuration c = getAboutMe();
        c.setValue(inputText);
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

    private Configuration getContactPhoneNumber(){
        Configuration contactPhoneNumber = configRepository.getDistinctByNameIsLike("contact_phone_number");
        if( contactPhoneNumber == null){
            contactPhoneNumber = new Configuration();
            contactPhoneNumber.setName("contact_phone_number");
            contactPhoneNumber.setValue("666-666-666");
            configRepository.save(contactPhoneNumber);
        }
        return contactPhoneNumber;
    }

    public String getStringContactPhoneNumber(){
        return getContactPhoneNumber().getValue();
    }

    public void setContactPhoneNumber(String phoneNumber){
        Configuration contactPhoneNumber = getContactPhoneNumber();
        contactPhoneNumber.setValue(phoneNumber);
        configRepository.save(contactPhoneNumber);
    }

    private Configuration getContactEmail(){
        Configuration contactEmail = configRepository.getDistinctByNameIsLike("contact_email");
        if( contactEmail == null){
            contactEmail = new Configuration();
            contactEmail.setName("contact_email");
            contactEmail.setValue("email@email.email");
            configRepository.save(contactEmail);
        }
        return contactEmail;
    }

    public String getStringContactEmail(){
        return getContactEmail().getValue();
    }

    public void setContactEmail(String email){
        Configuration contactEmail = getContactEmail();
        contactEmail.setValue(email);
        configRepository.save(contactEmail);
    }

}
