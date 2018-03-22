/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Dawn
 */
public class GenericTableDataModel implements Serializable {

    static public class DataModel implements Serializable {  
        private boolean selected = true;
        private Map<String,String> fields;  

        public DataModel(Map<String,String> fields) {  
            this.fields = fields;  
        }  

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public Map<String, String> getFields() {
            return fields;
        }

        public void setFields(Map<String, String> fields) {
            this.fields = fields;
        }
    }

    static public class ColumnModel implements Serializable {  
        private String header;  
        private String property;  

        public ColumnModel(String header, String property) {  
            this.header = header;  
            this.property = property;  
        }  

        public String getHeader() {  
            return header;  
        }

        public String getProperty() {  
            return property;  
        }  
    }

    private List<ColumnModel> columns;
    private List<DataModel> data;

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }

    public int getSelectedDataSize() {
        int count = 0;
        if ( (data != null) ) {
            for ( DataModel model : data ) {
                if ( model.isSelected() ) {
                    count++;
                }
            }
        }
        return count;
    }
}
