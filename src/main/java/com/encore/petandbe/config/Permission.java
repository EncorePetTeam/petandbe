package com.encore.petandbe.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.encore.petandbe.model.user.user.Role;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
	Role role() default Role.USER;
}
