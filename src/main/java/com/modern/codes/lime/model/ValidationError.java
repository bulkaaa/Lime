package com.modern.codes.lime.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of field validation errors.
 *
 * @author jaroszk
 */
@ApiModel(description = "Model representation of field validation errors")
public class ValidationError implements Serializable {

    /**
     * Instantiates a new Validation error.
     *
     * @param field the field
     * @param code  the code
     * @param hint  the hint
     */
    public ValidationError(final String field, final String code, final String hint) {
        this.field = field;
        this.code = code;
        this.hint = hint;
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets hint.
     *
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @ApiModelProperty("The field name on which the validation error occurred.")
    private final String field;
    @ApiModelProperty("The validation rule code that raised the error.")
    private final String code;
    @ApiModelProperty("A textual hint on why the validation failed.")
    private final String hint;
    private static final long serialVersionUID = -5228076972000583809L;

}
