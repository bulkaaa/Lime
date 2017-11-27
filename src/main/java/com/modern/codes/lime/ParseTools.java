package com.modern.codes.lime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modern.codes.lime.exception.IllegalDataException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ParseTools {
    private static ModelMapper modelMapper = new ModelMapper();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Logger logger = LoggerFactory.getLogger(ParseTools.class);

    public static<T> T parse (Object obj, Class<T> cl){
        if(null == obj)
            return null;
        try {
            return modelMapper.map(obj, cl);
        }catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }

    public static <T> List<T>  parseList(List<?> list, Class<T> cl){
        if(list == null)
            return new ArrayList<>();
        return list.stream().map(t-> ParseTools.parse(t, cl)).collect(Collectors.toList());
    }
    private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }
    public static <T> List<T> parseListObject(List<Object> o, Class<T> cl){
        return o.stream().map(t -> convertInstanceOfObject(t, cl)).collect(Collectors.toList());
    }
    // Pattern "yyyy-MM-dd HH:mm:ss"
    public static Date parseDate(String date){
        try {
            return dateFormat.parse(date);
        }
        catch(ParseException e){
            logger.error("trying to parse wrong patern string date");
            throw new IllegalDataException("trying to parse wrong patern string date");
        }
    }
    public static String parseDate(Date date){
        return date.toString();
    }
    public static <T> String parseToJson(Object obj, Class<T> cl){

        if(cl.toString().contains("POJO"))
            obj = parse(obj, cl);
        if(obj instanceof List) {
            List<Object> list = (List<Object>)obj;
            if(!list.isEmpty() && list.get(0).getClass().toString().contains("POJO"))
            obj = parseList(list, cl);
        }
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
