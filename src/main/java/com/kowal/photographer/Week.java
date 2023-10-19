package com.kowal.photographer;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odwzorowująca tydzień miesiąca.
 */
@Data
@ToString
public class Week {
    private final List<Day> listOfDays;

    /**
     * Konstruktor tworzący każdy oprócz pierwszego tygodnia miesiąca.
     * @param firstDayNumber numer pierwszego dnia tego tygodnia.
     * @param monthLength ilość dni w miesiącu, dla którego powstaje ten obiekt.
     */
    public Week(Integer firstDayNumber, Integer monthLength){
        listOfDays = new ArrayList<>();
        for (int i = 1; i <= 7 && i + firstDayNumber <= monthLength; i++){
            listOfDays.add( new Day( i, firstDayNumber + i - 1));
        }
    }


    /**
     * Specjalny konstruktor produkujący pierwszy tydzień miesiąca.
     * @param firstDayNumber numer pierwszego dnia tego tygodnia.
     * @param monthLength ilość dni w miesiącu, dla którego powstaje ten obiekt.
     * @param dayOfWeekNumber numer dnia tygodnia, w którym się zaczyna miesiąc np. czwartek = 4, piątek = 5 etc.
     */
    public Week(Integer firstDayNumber, Integer monthLength, Integer dayOfWeekNumber){
        listOfDays = new ArrayList<>();
        for (int i = dayOfWeekNumber; i <= 7 && i <= monthLength; i++){
            listOfDays.add( new Day( i, firstDayNumber + i - 1));
        }
    }
}
