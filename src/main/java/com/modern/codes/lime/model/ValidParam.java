package com.modern.codes.lime.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of parameter validation errors.
 *
 * @author jaroszk
 */

public class ValidParam implements Serializable {

    public List<ValidationError> getValidationErrors() {
        return this.validationErrors;
    }

    public void setValidationErrors(final List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    @ApiModelProperty("A list of form attributes that failed to validate.")
    @JsonProperty("validationErrors")
    private List<ValidationError> validationErrors;

}
