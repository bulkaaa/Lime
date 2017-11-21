package com.modern.codes.lime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Model representation of field validation errors.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of field validation errors")
public class ValidationError implements Serializable {

    private static final long serialVersionUID = -5228076972000583809L;

    @ApiModelProperty(value = "The field name on which the validation error occurred.")
    private String field = null;
    @ApiModelProperty(value = "The validation rule code that raised the error.")
    private String code = null;
    @ApiModelProperty(value = "A textual hint on why the validation failed.")
    private String hint = null;

    public ValidationError(String field, String code, String hint) {
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
