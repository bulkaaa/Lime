package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.util.List;

/**
 * Model representation of parameter validation errors.
 *
 * @author jaroszk
 *
 */

public class ValidParam implements Serializable {

    @ApiModelProperty("A list of form attributes that failed to validate.")
    @JsonProperty("validationErrors")
    private  List<ValidationError> validationErrors;

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

}
