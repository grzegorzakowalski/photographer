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
 * Klasa, która odwzorowuje miesiąc. Ma nazwę po polsku i listę tygodni. Jej głównym zadaniem jest ułatwić poprawne wyświetlanie miesiąca w kalendarzu.
 */
@Data
@ToString
public class Month {
    private final List<Week> listOfWeeks;
    private final String name;

    /**
     * Konstruktor tworzący całą strukturę miesiąca.
     * @param localDate zmienna, na bazie której wiadomo, który miesiąc stworzyć.
     */
    public Month(LocalDate localDate){
        listOfWeeks = new ArrayList<>();
        name = getMonthNameInPolish(localDate);
        localDate = localDate.withDayOfMonth(1);
        MonthService monthService = new MonthService(localDate);
        listOfWeeks.add(new Week(1, monthService.getMonthLength(), monthService.getFirstDayAsNumberOfWeekDay()));
        LocalDate end = localDate.plusMonths(1);
        localDate = localDate.plusDays(7 - monthService.getFirstDayAsNumberOfWeekDay());
        while(localDate.isBefore( end) && !localDate.isEqual(end.minusDays(1))){
            listOfWeeks.add( new Week(localDate.getDayOfMonth(), localDate.lengthOfMonth()));
            localDate = localDate.plusDays(7);
        }
    }

    /**
     * Metoda zwracająca nazwę miesiąca po polsku.
     * @param localDate obiekt, na bazie którego wiadomo jakiego miesiąca nazwę przetłumaczyć.
     * @return Spolszczoną nazwę miesiąca.
     */
    private String getMonthNameInPolish(LocalDate localDate){
        return StringUtils.capitalize(localDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")));
    }

}
