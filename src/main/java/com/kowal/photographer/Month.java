package com.kowal.photographer;

import com.kowal.photographer.services.MonthService;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@ToString
public class Month {
    private final List<Week> listOfWeeks;
    private final String name;

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

    private String getMonthNameInPolish(LocalDate localDate){
        switch (localDate.getMonth().getValue()){
            case 1: return "Styczeń";

            case 2: return "Luty";

            case 3: return "Marzec";

            case 4: return "Kwiecień";

            case 5: return "Maj";

            case 6: return "Czerwiec";

            case 7: return "Lipiec";

            case 8: return "Sierpień";

            case 9: return "Wrzesień";

            case 10: return "Październik";

            case 11: return "Listopad";

            case 12: return "Grudzień";

        }
        return "Jak to się pojawiło to jest na prawdę źle";
    }

}
