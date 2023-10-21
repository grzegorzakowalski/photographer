package com.kowal.photographer;

import com.kowal.photographer.services.MonthService;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Class representing given month in a year.
 */
@Data
@ToString
public class Month {
    private final List<Week> listOfWeeks;
    private final String name;
    private final Integer firstDay;
    private final Integer length;

    /**
     * Constructor.
     * @param localDate object with month that is meant to be represented as.
     */
    public Month(LocalDate localDate){
        listOfWeeks = new ArrayList<>();
        name = getMonthNameInPolish(localDate);
        localDate = localDate.withDayOfMonth(1);
        MonthService monthService = new MonthService(localDate);
        firstDay = monthService.getFirstDayAsNumberOfWeekDay();
        length = monthService.getMonthLength();
        listOfWeeks.add(new Week(1, length, firstDay));
        LocalDate end = localDate.plusMonths(1);
        localDate = localDate.plusDays(7 - firstDay);
        while(localDate.isBefore( end) && !localDate.isEqual(end.minusDays(1))){
            listOfWeeks.add( new Week(localDate.getDayOfMonth(), length));
            localDate = localDate.plusDays(7);
        }
    }

    /**
     * Method translates month name from english to Polish.
     * @param localDate object with month name to translate.
     * @return Polish name of given month.
     */
    private String getMonthNameInPolish(LocalDate localDate){
        return StringUtils.capitalize(localDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")));
    }

}
