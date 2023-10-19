package com.kowal.photographer;

import lombok.Data;

/**
 * Klasa trzymająca w sobie wszystkie ustawienia strony.
 */
@Data
public class PageSettings {
    private String aboutMe;
    private String siteColor;
    private String contactPhoneNumber;
    private String contactEmail;

}
