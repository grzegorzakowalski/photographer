package com.kowal.photographer.services;

import com.kowal.photographer.pojo.PageSettings;
import com.kowal.photographer.entities.Configuration;
import com.kowal.photographer.repositorys.ConfigRepository;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Serwis zajmujący się obsługą konfiguracji.
 */
@Slf4j
@Service
public class ConfigurationService {

    private final ConfigRepository configRepository;

    @Getter
    private static final Map<String,String> colorMap = new HashMap<>();

    public ConfigurationService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
        colorMap.put("red","is-danger");
        colorMap.put("yellow","is-warning");
        colorMap.put("green","is-success");
        colorMap.put("blue","is-info");
        colorMap.put("darkBlue","is-link");
        colorMap.put("darkGreen","is-primary");
    }

    /**
     * Method creates configuration if there is none.
     */
    public void checkConfigurationIfEmptyCreate(){
        getMaxPerDay();
        getSiteColor();
        getAboutMe();
        getContactPhoneNumber();
        getContactHours();
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

    public String getStringSiteColor(){
        return getSiteColor().getValue();
    }

    public String getSiteColorAsName(){
        String siteColor = getStringSiteColor();
        return colorMap.entrySet().stream()
                .filter( entry -> entry.getValue().equals(siteColor))
                .findFirst().orElse(colorMap.entrySet().stream().findFirst().get()).getKey();
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

    public void setMaxPerDay(String maxPerDay){
        Configuration c = getMaxPerDay();
        c.setValue(maxPerDay);
        configRepository.save(c);
    }

    public Integer getIntegerMaxPerDay(){
        return Integer.parseInt(getMaxPerDay().getValue());
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

    public void setAboutMe(String inputText){
        Configuration c = getAboutMe();
        c.setValue(inputText);
        configRepository.save(c);
    }

    public String getStringAboutMe(){
        return getAboutMe().getValue();
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

    public void setContactPhoneNumber(String phoneNumber){
        Configuration contactPhoneNumber = getContactPhoneNumber();
        contactPhoneNumber.setValue(phoneNumber);
        configRepository.save(contactPhoneNumber);
    }

    public String getStringContactPhoneNumber(){
        return getContactPhoneNumber().getValue();
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

    public void setContactEmail(String email){
        Configuration contactEmail = getContactEmail();
        contactEmail.setValue(email);
        configRepository.save(contactEmail);
    }

    public String getStringContactEmail(){
        return getContactEmail().getValue();
    }

    private Configuration getContactHours(){
        Configuration contactHours = configRepository.getDistinctByNameIsLike("contact_hours");
        if( contactHours == null){
            contactHours = new Configuration();
            contactHours.setName("contact_hours");
            contactHours.setValue("08:00 - 20:00");
            configRepository.save(contactHours);
        }
        return contactHours;
    }

    public void setContactHours(String hours){
        Configuration contactHours = getContactHours();
        contactHours.setValue(hours);
        configRepository.save(contactHours);
    }

    public String getStringContactHours(){
        return getContactHours().getValue();
    }

    /**
     * Method returns full configuration as PageSettings object.
     * @return PageSettings object.
     */
    @NotNull
    public PageSettings getPageSettings(){
        PageSettings pageSettings = new PageSettings();
        pageSettings.setAboutMe(getStringAboutMe());
        pageSettings.setSiteColor(getStringSiteColor());
        pageSettings.setContactEmail(getStringContactEmail());
        pageSettings.setContactPhoneNumber(getStringContactPhoneNumber());
        pageSettings.setContactHours(getStringContactHours());
        return pageSettings;
    }

    /**
     * Method saves settings to database from PageSettings object.
     * @param pageSettings with configuration to save.
     */
    public void savePageSettings(PageSettings pageSettings){
        setAboutMe(pageSettings.getAboutMe() == null ? getStringAboutMe() : pageSettings.getAboutMe());
        setSiteColor(pageSettings.getSiteColor() == null ? getStringSiteColor() : pageSettings.getSiteColor());
        setContactEmail(pageSettings.getContactEmail() == null ? getStringContactEmail() : pageSettings.getContactEmail());
        setContactPhoneNumber(pageSettings.getContactPhoneNumber() == null ? getStringContactPhoneNumber() : pageSettings.getContactPhoneNumber());
        setContactHours(pageSettings.getContactHours() == null ? getStringContactHours() : pageSettings.getContactHours());
    }
}
