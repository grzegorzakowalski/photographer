package com.kowal.photographer.services;

import com.kowal.photographer.entities.Configuration;
import com.kowal.photographer.pojo.PageSettings;
import com.kowal.photographer.repositorys.ConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConfigurationServiceTests {

    @Mock
    private ConfigRepository mockConfigRepository;

    @InjectMocks
    private ConfigurationService configurationService;

    private void setUpEmptyConfiguration(){
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("site_color")).thenReturn(null);
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("max_per_day")).thenReturn(null);
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("about_me")).thenReturn(null);
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("contact_phone_number")).thenReturn(null);
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("contact_email")).thenReturn(null);
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("contact_hours")).thenReturn(null);
    }

    private void setUpConfiguration(){
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("site_color")).thenReturn(Configuration.builder().id(2L).name("site_color").value("is-warning").build());
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("max_per_day")).thenReturn(Configuration.builder().id(1L).name("max_per_day").value("3").build());
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("about_me")).thenReturn(Configuration.builder().id(3L).name("about_me").value("coś tam jest").build());
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("contact_phone_number")).thenReturn(Configuration.builder().id(4L).name("contact_phone_number").value("666").build());
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("contact_email")).thenReturn(Configuration.builder().id(5L).name("contact_email").value("foo@baa").build());
        Mockito.when( mockConfigRepository.getDistinctByNameIsLike("contact_hours")).thenReturn(Configuration.builder().id(6L).name("contact_hours").value("69:69-69:69").build());
    }
    @Test
    public void givenEmptyConfiguration_WillCreateNewOne(){
        setUpEmptyConfiguration();
        try{
            configurationService.checkConfigurationIfEmptyCreate();
        } catch (Exception e){
            fail("Exception was thrown:" + e.getMessage());
        }
    }

    @Test
    public void givenFullConfiguration_WillReturnCorrectPageSettings(){
        setUpConfiguration();
        PageSettings pageSettings = null;
        PageSettings correctPageSettings = PageSettings.builder().maxPerDay("3")
                .siteColor("is-warning")
                .aboutMe("coś tam jest")
                .contactPhoneNumber("666")
                .contactEmail("foo@baa")
                .contactHours("69:69-69:69").build();
        try {
            pageSettings = configurationService.getPageSettings();
        } catch (Exception e){
            fail("Exception was thrown:" + e.getMessage());
        }
        assertEquals(correctPageSettings.getMaxPerDay() , pageSettings.getMaxPerDay());
        assertEquals(correctPageSettings.getSiteColor() , pageSettings.getSiteColor());
        assertEquals(correctPageSettings.getAboutMe() , pageSettings.getAboutMe());
        assertEquals(correctPageSettings.getContactPhoneNumber() , pageSettings.getContactPhoneNumber());
        assertEquals(correctPageSettings.getContactEmail() , pageSettings.getContactEmail());
        assertEquals(correctPageSettings.getContactHours() , pageSettings.getContactHours());
    }

    @Test
    public void givenEmptyConfiguration_WillReturnDefaultConfigurationInPageSettings(){
        setUpEmptyConfiguration();
        PageSettings pageSettings = null;
        PageSettings correctPageSettings = PageSettings.builder().maxPerDay("3")
                .siteColor("is-info")
                .aboutMe("Tutaj coś do powiedzenia o sobie")
                .contactPhoneNumber("666-666-666")
                .contactEmail("email@email.email")
                .contactHours("08:00 - 20:00").build();
        try {
            pageSettings = configurationService.getPageSettings();
        } catch (Exception e){
            fail("Exception was thrown:" + e.getMessage());
        }
        assertEquals(correctPageSettings.getMaxPerDay() , pageSettings.getMaxPerDay());
        assertEquals(correctPageSettings.getSiteColor() , pageSettings.getSiteColor());
        assertEquals(correctPageSettings.getAboutMe() , pageSettings.getAboutMe());
        assertEquals(correctPageSettings.getContactPhoneNumber() , pageSettings.getContactPhoneNumber());
        assertEquals(correctPageSettings.getContactEmail() , pageSettings.getContactEmail());
        assertEquals(correctPageSettings.getContactHours() , pageSettings.getContactHours());
    }
}
