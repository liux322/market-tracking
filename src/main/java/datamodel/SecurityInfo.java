/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *
 * @author Dawn
 */
public class SecurityInfo {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityInfo.class);
    private String ticker;
    private double price;
    private double change;
    private String percentage;
    private String dailyRange;
    private String annulRange;
    private double yearLow;
    private double yearHigh;
    private double dividend;
    private double yield;
    private String volumn;
    private String url;
    private Date exDividendDate;    
    private boolean owned;    
    private double myChange;
    private double myPrice;
    private long id;
    private Date tradingDate;
    private String action;
    private int shares;
    private double commission;
    private String comments;

    public String getAnnulRange() {
        return annulRange;
    }

    public void setAnnulRange(String annulRange) {
        this.annulRange = annulRange;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getDailyRange() {
        return dailyRange;
    }

    public void setDailyRange(String dailyRange) {
        this.dailyRange = dailyRange;
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public double getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(double yearHigh) {
        this.yearHigh = yearHigh;
    }

    public double getYearLow() {
        return yearLow;
    }

    public void setYearLow(double yearLow) {
        this.yearLow = yearLow;
    }

    public String getVolumn() {
        return volumn;
    }

    public void setVolumn(String volumn) {
        this.volumn = volumn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExDividendDate() {
        return exDividendDate;
    }

    public void setExDividendDate(Date exDividendDate) {
        this.exDividendDate = exDividendDate;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public double getMyChange() {
        return myChange;
    }

    public void setMyChange(double myChange) {
        this.myChange = myChange;
    }

    public double getMyPrice() {
        return myPrice;
    }

    public void setMyPrice(double myPrice) {
        this.myPrice = myPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
        
    @Override
    public String toString()
    {
        return ticker + ": " + price + " " + change + " " + percentage + "\n";
    }

    public String showDetail(){
        return ticker + ": " + price + " " + change + " " + percentage + "\n"
               + "Range: " + dailyRange +"\n"
               + "Year Range: " + annulRange +"\n"
               + "D: " +dividend + " Y: "+ yield +"\n"
               + ((exDividendDate == null )?"": "Next ex Div Date: "+exDividendDate +"\n")
               + "My Change : " + myChange +  "\n"
               + "My Id = " + id + "\n";
    }
    
    public String showTrade()
    {
        return ticker + ": " + action + " " + shares + " " + myPrice + " " + commission;
    }
}
