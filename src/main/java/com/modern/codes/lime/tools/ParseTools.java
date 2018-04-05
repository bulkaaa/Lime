package com.modern.codes.lime.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modern.codes.lime.exception.IllegalDataException;

/**
 * The type Parse tools.
 */
@Service
public class ParseTools {
    private ParseTools() {
    }

    /**
     * Parse t.
     *
     * @param <T> the type parameter
     * @param obj the obj
     * @param cl  the cl
     * @return the t
     */
    public static <T> T parse(final Object obj, final Class<T> cl) {
        if (null == obj) {
            return null;
        }
        try {
            return modelMapper.map(obj, cl);
        } catch (final Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }

    /**
     * Parse list list.
     *
     * @param <T>  the type parameter
     * @param list the list
     * @param cl   the cl
     * @return the list
     */
    public static <T> List<T> parseList(final List<?> list, final Class<T> cl) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list.stream()
                   .map(t -> ParseTools.parse(t, cl))
                   .collect(Collectors.toList());
    }

    /**
     * Parse list object list.
     *
     * @param <T> the type parameter
     * @param o   the o
     * @param cl  the cl
     * @return the list
     */
    public static <T> List<T> parseListObject(final List<?> o, final Class<T> cl) {
        return o.stream()
                .map(t -> convertInstanceOfObject(t, cl))
                .collect(Collectors.toList());
    }

    /**
     * Parse date date.
     *
     * @param date the date
     * @return the date
     */
    // Pattern "yyyy-MM-dd HH:mm:ss"
    public static Date parseDate(final String date) {
        try {
            return dateFormat.parse(date);
        } catch (final ParseException e) {
            logger.error("trying to parse wrong patern string date");
            throw new IllegalDataException("trying to parse wrong patern string date");
        }
    }

    /**
     * Parse date string.
     *
     * @param date the date
     * @return the string
     */
    public static String parseDate(final Date date) {
        return date.toString();
    }

    /**
     * Parse to json string.
     *
     * @param <T> the type parameter
     * @param obj the obj
     * @param cl  the cl
     * @return the string
     */
    public static <T> String parseToJson(Object obj, Class<T> cl) {

        if (obj.getClass()
               .toString()
               .contains("POJO")) {
            obj = parse(obj, cl);
        }
        if (obj instanceof List) {
            final List<Object> list = (List<Object>) obj;
            if (!list.isEmpty() && list.get(0)
                                       .getClass()
                                       .toString()
                                       .contains("POJO")) {
                obj = parseList(list, cl);
            }
        }
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (final Exception e) {
            return e.getMessage();
        }
    }

    private static <T> T convertInstanceOfObject(final Object o, final Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (final ClassCastException e) {
            return null;
        }
    }

    private static final ModelMapper modelMapper = new ModelMapper();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(ParseTools.class);
}