package utils;

import au.com.bytecode.opencsv.CSVReader;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datamodel.GenericTableDataModel;
import datamodel.TransactionRecord;

/**
 * Created by Dliu on 9/12/2016.
 */
public class CommonCSVFileLoader {

    private String filePath;

    public CommonCSVFileLoader(String filePath){
        this.filePath = filePath;
    }

    public List<TransactionRecord> readCSVFile(){
        List<TransactionRecord> resultList = new ArrayList(); //Collections.emptyList();
        File dropFolder = new File(filePath);
        GenericTableDataModel orderData = new GenericTableDataModel();
        try {
            //CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(dropFolder)), '\t');
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(dropFolder)), ',');
            if(reader == null){
                reader = new CSVReader(new InputStreamReader(new FileInputStream(dropFolder)));
            }
            boolean haveHeader = false;
            String[] nextLine;

            orderData.setColumns(new ArrayList<GenericTableDataModel.ColumnModel>());
            orderData.setData(new ArrayList<GenericTableDataModel.DataModel>());
            while ((nextLine = reader.readNext()) != null) {
                if ( !haveHeader ) {
                    for ( int i = 0; i < nextLine.length; i++ ) {
                        orderData.getColumns().add(new GenericTableDataModel.ColumnModel(nextLine[i],""+i));
                    }
                    haveHeader = true;
                } else {
                    boolean emptyLine = true;
                    Map<String,String> dataMap = new HashMap<String,String>();
                    for ( int i = 0; i < nextLine.length; i++ ) {
                        if ( StringUtils.isNotBlank(nextLine[i]) ) {
                            emptyLine = false;
                        }
                        dataMap.put(""+i, nextLine[i]);
                    }
                    if ( !emptyLine ) {
                        orderData.getData().add(new GenericTableDataModel.DataModel(dataMap));
                    }
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<GenericTableDataModel.ColumnModel> columns = orderData.getColumns();
        List<GenericTableDataModel.DataModel> datas = orderData.getData();

        datas.stream().forEach(row -> {
            TransactionRecord record = new TransactionRecord(columns, row);
            resultList.add(record);
        });
        return resultList;
    }
}
