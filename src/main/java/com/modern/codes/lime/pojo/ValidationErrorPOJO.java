package com.modern.codes.lime.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ValidationErrorPOJO {
    private String field = null;
    private String code = null;
    private String hint = null;

    public ValidationErrorPOJO(String field, String code, String hint) {
        this.field = field;
        this.code = code;
        this.hint = hint;
    }

    public String getField() {
        return field;
    }
    public String getCode() {
        return code;
    }
    public String getHint() {
        return hint;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
