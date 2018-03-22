package datamodel;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

//import java.util.Date;
//import lombok.Data;
/**
 * Created by Dliu on 9/12/2016.
 */

//@Data
public class TransactionRecord {
    private LocalDate tranactionDate;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private long volume;

    public TransactionRecord (List<GenericTableDataModel.ColumnModel> fields, GenericTableDataModel.DataModel data){
        //SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy") ;
        DateTimeFormatter dateFormatters = DateTimeFormatter.ofPattern("d-MMM-yy");

        fields.stream().forEach(field ->{
            String theData = data.getFields().get(field.getProperty());
            String header = field.getHeader().trim();
            if(StringUtils.endsWithIgnoreCase(header, "Date")){
                try {
                    //tranactionDate = dateFormatter.parse(theData);
                    tranactionDate = LocalDate.parse(theData.trim(), dateFormatters);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            }

            if(StringUtils.equalsIgnoreCase(header, "Open")){
                try {
                    openPrice = Double.valueOf(theData);
                } catch (NumberFormatException e) {
                   // e.printStackTrace();
                    openPrice =0.0;
                }
            }

            if(StringUtils.equalsIgnoreCase(header, "High")){
                try {
                    highPrice = Double.valueOf(theData);
                } catch (NumberFormatException e) {
                    //e.printStackTrace();
                    highPrice = 0.0;
                }
            }

            if(StringUtils.equalsIgnoreCase(header, "Low")){
                try {
                    lowPrice = Double.valueOf(theData);
                } catch (NumberFormatException e) {
                 //   e.printStackTrace();
                    lowPrice =0.0;
                }
            }

            if(StringUtils.equalsIgnoreCase(header, "Close")){
                try {
                    closePrice = Double.valueOf(theData);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    closePrice = 0.0;
                }
            }

            if(StringUtils.equalsIgnoreCase(header, "Volume")){
                try {
                    volume = Integer.valueOf(theData);
                } catch (NumberFormatException e) {
                    //e.printStackTrace();
                    volume =0;
                }
            }
        });
    }

    public LocalDate getTranactionDate() {
        return tranactionDate;
    }

    public void setTranactionDate(LocalDate tranactionDate) {
        this.tranactionDate = tranactionDate;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}
