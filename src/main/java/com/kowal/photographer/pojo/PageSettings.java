package com.kowal.photographer.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class holding site configuration.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSettings {
    private String maxPerDay;
    private String aboutMe;
    private String siteColor;
    private String contactPhoneNumber;
    private String contactEmail;
    private String contactHours;
}
