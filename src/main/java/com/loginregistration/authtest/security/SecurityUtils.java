package com.loginregistration.authtest.security;

import com.loginregistration.authtest.business.CustomUserDetails;
import com.loginregistration.authtest.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class SecurityUtils {
    public static Optional<Authentication> getAuthentication() {
        return ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication);
    }

    public static Optional<CustomUserDetails> getUserDetails() {
        return getAuthentication()
                .map(Authentication::getPrincipal)
                .filter(CustomUserDetails.class::isInstance)
                .map(CustomUserDetails.class::cast);
    }

    public static Optional<User> getUser() {
        return getUserDetails()
                .map(CustomUserDetails::getUser);
    }
}
