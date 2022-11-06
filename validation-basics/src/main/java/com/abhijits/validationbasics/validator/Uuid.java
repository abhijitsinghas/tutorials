package com.abhijits.validationbasics.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by   : Abhijit Singh
 * On           : 28 October, 2022
 *
 * Annotation to validate Uuid content.
 * Validation is available for List of Uuids as Json or for single Uuid string.
 */
@Target({METHOD, FIELD, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {Uuid.UuidValidator.class})
public @interface Uuid {

    /**
     * Expected Class to transform from JSON.
     * Options: List.class, String.class.
     *
     * @return Class object
     */

    boolean required() default false;

    String message() default "'${validatedValue}' is not a valid uuid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UuidValidator implements ConstraintValidator<Uuid, CharSequence> {

        private boolean required;

        @Override
        public void initialize(Uuid parameters) {
            required = parameters.required();
        }

        @Override
        public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
            if (required == false && value == null) {
                return true;
            }

            if (required && value == null) {
                return false;
            }

            if (!value.toString().matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")) {
                return false;
            }

            return true;
        }
    }
}
