package com.truextend.dev.recipes.util;

import com.truextend.dev.recipes.model.MessageError;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecipesUtil {

    public MessageError getFormatMessage(String messageHash, HttpStatus httpStatus){
        MessageError messages = null;
        if(messageHash == null){
            messageHash = ConstantsRecipes.EMPTY_VALUE;
        }

        messages = new MessageError();
        messages.setStatus(httpStatus.value());
        messages.setMessage(messageHash);

        return messages;
    }

    /**
     * Method for convert data into string
     * @param fecha
     * @param format
     * @return
     */
    public String convertDateToString(Date fecha, String format){
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            result = simpleDateFormat.format(fecha);
        }catch (Exception er){

        }
        return result;
    }

    /**
     * Method for convert dat to string
     * @param time
     * @param format
     * @return
     */
    public Date convertStringToDate(String time, String format){
        Date result = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            result = simpleDateFormat.parse(time);
        }catch (Exception error){;
        }
        return result;
    }


    public Date setLastTime(Date date) {
        Date rDate = new Date();
        try {
            String temp = this.convertDateToString(date,ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD);
            rDate = this.convertStringToDate((temp + " " + ConstantsRecipes.LAST_TIME), ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);
        }catch (Exception erg){
            rDate = new Date();
        }
        return rDate;
    }
}
