package com.kowal.photographer;

import com.kowal.photographer.services.ConfigurationService;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ToString
public class PageSettings {
    private String aboutMe;
    private String color;
    private String contactPhoneNumber;
    private String contactEmail;

    private final ConfigurationService configurationService;

    public PageSettings(ConfigurationService configurationService) {
        this.configurationService = configurationService;
        aboutMe = configurationService.getStringAboutMe();
        color = configurationService.getStringSiteColor();
        contactPhoneNumber = configurationService.getStringContactPhoneNumber();
        contactEmail = configurationService.getStringContactEmail();
    }

    public void save(){
        configurationService.setAboutMe(aboutMe);
        configurationService.setSiteColor(color);
        configurationService.setContactEmail(contactEmail);
        configurationService.setContactPhoneNumber(contactPhoneNumber);
    }
}
