package com.javaspring.appserver.utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ObjectValidator {
	@Autowired
    @Qualifier("validator")
    LocalValidatorFactoryBean validatorFactory;

	public <T> String validateRequestThenReturnMessage(T t) {
        Set<ConstraintViolation<T>> violations = validatorFactory.getValidator().validate(t);
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<T> violation : violations) {
            messages.add(violation.getMessage());
        }
        String message = String.join(",", messages);
        if (message.contains(",")) {
            message = "[" + message + "]";
        }
        return message;
    }
}
