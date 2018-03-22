package datamodel;

import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * Created by Dliu on 9/12/2016.
 */
public class Trend {

    private LocalDate startDate;
    private LocalDate endDate;
    private int daysInTrend;
    private double firstDayPrice;
    private double lastDayPrice;
    private double firstDayPriceOfTheNextDay;

    NumberFormat percentFormat = NumberFormat.getPercentInstance();
    public Trend(){
        percentFormat.setMinimumFractionDigits(2);
    }



    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDaysInTrend() {
        return daysInTrend;
    }

    public void setDaysInTrend(int daysInTrend) {
        this.daysInTrend = daysInTrend;
    }

    public double getFirstDayPrice() {
        return firstDayPrice;
    }

    public void setFirstDayPrice(double firstDayPrice) {
        this.firstDayPrice = firstDayPrice;
    }

    public double getLastDayPrice() {
        return lastDayPrice;
    }

    public void setLastDayPrice(double lastDayPrice) {
        this.lastDayPrice = lastDayPrice;
    }

    public double getFirstDayPriceOfTheNextDay() {
        return firstDayPriceOfTheNextDay;
    }

    public void setFirstDayPriceOfTheNextDay(double firstDayPriceOfTheNextDay) {
        this.firstDayPriceOfTheNextDay = firstDayPriceOfTheNextDay;
    }

    @Override
    public String toString(){
        if(firstDayPrice == 0){firstDayPrice = 0.0001;}
        if(lastDayPrice == 0){lastDayPrice = 0.0001;}
        return "Total Days in Trend: " + daysInTrend + "\n" +
               "Start Date: " + startDate + "\n" +
               "End Date: " + endDate + "\n" +
                "change in the trend: " + percentFormat.format((lastDayPrice - firstDayPrice)/firstDayPrice) + "\n" +
                "change when the trend turned: " + percentFormat.format((firstDayPriceOfTheNextDay-lastDayPrice)/lastDayPrice) +"\n";
    }
}
