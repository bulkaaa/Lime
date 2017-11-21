package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.ValidationError;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ValidParamPOJO {
    private List<ValidationError> validationErrors;

    public List<ValidationError> getValidationErrors() {
        return this.validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
