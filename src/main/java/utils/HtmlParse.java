/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import datamodel.SecurityInfo;

/**
 *
 * @author Dawn
 */
public class HtmlParse {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlParse.class);
    private static String NUMBER_REGEX = "\\$?-?[.,\\d]+";
    private static String DIV_HISTORY_DATE_FORMAT = "MM/dd/yyyy";
    
    public String parse(BufferedReader source) throws Exception 
    {
        String sourceLine;
        String content = "";
        // Append each new HTML line into one string. Add a tab character.
        while ((sourceLine = source.readLine()) != null)
                content += sourceLine + "\t";

        // Remove style tags & inclusive content
        Pattern style = Pattern.compile("<style.*?>.*?</style>");
        Matcher mstyle = style.matcher(content);
        while (mstyle.find()) content = mstyle.replaceAll("");

        // Remove script tags & inclusive content
        Pattern script = Pattern.compile("<script.*?>.*?</script>");
        Matcher mscript = script.matcher(content);
        while (mscript.find()) content = mscript.replaceAll("");

        // Remove primary HTML tags
        Pattern tag = Pattern.compile("<.*?>");
        Matcher mtag = tag.matcher(content);
        while (mtag.find()) content = mtag.replaceAll("");

        // Remove comment tags & inclusive content
        Pattern comment = Pattern.compile("<!--.*?-->");
        Matcher mcomment = comment.matcher(content);
        while (mcomment.find()) content = mcomment.replaceAll("");

        // Remove special characters, such as &nbsp;
        Pattern sChar = Pattern.compile("&.*?;");
        Matcher msChar = sChar.matcher(content);
        while (msChar.find()) content = msChar.replaceAll("");

        // Remove the tab characters. Replace with new line characters.
        Pattern nLineChar = Pattern.compile("\t+");
        Matcher mnLine = nLineChar.matcher(content);
        while (mnLine.find()) content = mnLine.replaceAll("\n");
       // System.out.println(content); 
        return content;
    }
    
    public void parseInfo(String ticker, BufferedReader source, SecurityInfo securityInfo) throws Exception
    {
        String content = parse(source);

        BufferedReader txtIn = null;
        try
        {
            securityInfo.setTicker(ticker);
            txtIn = new BufferedReader(new StringReader(content));
            String line = txtIn.readLine();
            while(line != null)
            {
                if(StringUtils.equalsIgnoreCase(line.trim(), "Beta"))
                {
                    break;
                }

                if(StringUtils.equalsIgnoreCase(line.trim(), ticker))
                {
                    String priceLine = txtIn.readLine();
                    String changeLine = txtIn.readLine();
                    String percentageLine = txtIn.readLine();
                    try
                    {
                        securityInfo.setPrice(Double.parseDouble(priceLine));
                        securityInfo.setChange(Double.parseDouble(changeLine));
                        securityInfo.setPercentage(percentageLine);
                        if(securityInfo.getMyPrice() != 0)
                        {
                            double myChange =( securityInfo.getPrice()-securityInfo.getMyPrice() )/securityInfo.getMyPrice();
                            securityInfo.setMyChange(myChange);
                        }
                    }catch(NumberFormatException nue)
                    {
                        nue.printStackTrace();
                    }
                }

                if(StringUtils.equalsIgnoreCase(line.trim(), "Range"))
                {
                    String rangeLine = txtIn.readLine();
                    securityInfo.setDailyRange(rangeLine);
                }

                if(StringUtils.equalsIgnoreCase(line.trim(), "52 week"))
                {
                    String yearRangeLine = txtIn.readLine();
                    securityInfo.setAnnulRange(yearRangeLine);
                    String[] yearRanges = yearRangeLine.split("-");
                    try
                    {
                        securityInfo.setYearLow(Double.parseDouble(yearRanges[0]));
                        securityInfo.setYearHigh(Double.parseDouble(yearRanges[1]));
                    }catch(NumberFormatException nue)
                    {
                        nue.printStackTrace();
                    }
                }
                if(StringUtils.equalsIgnoreCase(line.trim(), "Vol / Avg."))
                {
                    String volumnLine = txtIn.readLine();
                    securityInfo.setVolumn(volumnLine);
                }
                if(StringUtils.equalsIgnoreCase(line.trim(), "Div/yield"))
                {
                    String divLine = txtIn.readLine();
                    if(divLine.indexOf("/")>0)
                    {
                        String[] divs = divLine.split("/");
                        try
                        {
                            securityInfo.setDividend(Double.parseDouble(divs[0]));
                            securityInfo.setYield(Double.parseDouble(divs[1]));
                        }catch(NumberFormatException nue)
                        {
                            //nue.printStackTrace();
                        }
                    }
                }
                line = txtIn.readLine();
            }

            //System.out.println(securityInfo);

        }finally
        {
            if(txtIn != null)
            {
                txtIn.close();
            }
        }
    }

//    public List<DividendHistory> parseDivHistory(String ticker, BufferedReader source) throws Exception
//    {
//        String content = parse(source);
////        System.out.println("content =" + content);
//        BufferedReader txtIn = null;
//        List<DividendHistory> resultList = new ArrayList<DividendHistory>();
//        try
//        {
//            txtIn = new BufferedReader(new StringReader(content));
//            String line = txtIn.readLine();
//            while(line != null)
//            {
//                if(StringUtils.equals(line, "NASDAQ OMX"))
//                {
//                    break;
//                }
//
//                if(StringUtils.equalsIgnoreCase(line.trim(), "Ex/Eff DateTypeCash AmountDeclaration DateRecord DatePayment Date"))
//                {
//                    line = txtIn.readLine();
//                    while(StringUtils.isNotBlank(line))
//                    {
//                        String exDivDateline = line;
//                        //skip "cash"
//                        txtIn.readLine();
//                        String priceLine = txtIn.readLine();
//                        //skip empty line
//                        txtIn.readLine();
//                        String declarationDateStr = txtIn.readLine();
//                        //skip empty line
//                        txtIn.readLine();
//                        String recordDateStr = txtIn.readLine();
//                        //skip empty line
//                        txtIn.readLine();
//                        String paymentDateStr = txtIn.readLine();
//                        try
//                        {
//                            DividendHistory divHistory = new DividendHistory();
//                            divHistory.setTicker(ticker);
//                            String[] exdivLines = StringUtils.split(exDivDateline);
//                            if(!StringUtils.equals(exdivLines[0].trim(), "--"))
//                            {
//                                Date exiDivDate = DateFormatter.getInstance().getDate(exdivLines[0], DIV_HISTORY_DATE_FORMAT);
//                                divHistory.setExDividendDate(exiDivDate);
//                            }
//
//                            double price = 0.0;
//                            if(!StringUtils.equals(priceLine.trim(), "--"))
//                            {
//                                price = Double.parseDouble(priceLine);
//                            }
//                            divHistory.setPayment(price);
//
//                            if(!StringUtils.equals(declarationDateStr.trim(), "--"))
//                            {
//                                Date declarationDate = DateFormatter.getInstance().getDate(declarationDateStr, DIV_HISTORY_DATE_FORMAT);
//                                divHistory.setDeclareDate(declarationDate);
//                            }
//
//                            if(!StringUtils.equals(recordDateStr.trim(), "--"))
//                            {
//                                Date recortDate = DateFormatter.getInstance().getDate(recordDateStr, DIV_HISTORY_DATE_FORMAT);
//                                divHistory.setRecordDate(recortDate);
//                            }
//
//                            if(!StringUtils.equals(paymentDateStr.trim(), "--"))
//                            {
//                                Date paymentDate = DateFormatter.getInstance().getDate(paymentDateStr, DIV_HISTORY_DATE_FORMAT);
//                                divHistory.setPayableDate(paymentDate);
//                            }
//
//                            resultList.add(divHistory);
//
//                            line = txtIn.readLine();
//                        }catch(Exception ex)
//                        {
//                            ex.printStackTrace();
//                        }
//                    }
//                }
//                line = txtIn.readLine();
//            }
//        }finally
//        {
//            if(txtIn != null)
//            {
//                txtIn.close();
//            }
//        }
//        return resultList;
//
//    }
}
