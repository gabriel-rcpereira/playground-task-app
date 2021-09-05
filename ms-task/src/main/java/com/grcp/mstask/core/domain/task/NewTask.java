package com.grcp.mstask.core.domain.task;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NewTask {

    @NotBlank
    private final String userId;
    @NotBlank
    @Size(min = 1, max = 150)
    private final String description;

    public NewTask(String userId,
                   String description) {
        this.userId = userId;
        this.description = description;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var constraints = validator.validate(this);
        if (!constraints.isEmpty()) {
            throw new ConstraintViolationException(constraints);
        }
    }
}
