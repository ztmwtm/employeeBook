package com.example.employeebook.service;

import com.example.employeebook.exception.IncorrectInputException;
import org.apache.commons.lang3.StringUtils;

public class EmployeeUtils {

    private EmployeeUtils() {
    }

    public static String validateString(String data) throws IncorrectInputException {
        data = StringUtils.deleteWhitespace(data);
        if (!StringUtils.isAlpha(data)) {
            throw new IncorrectInputException("Incorrect data input in string " + data + " should contains only [a-zA-Z]");
        }
        data = StringUtils.lowerCase(data);
        data = StringUtils.capitalize(data);
        return data;
    }
}
