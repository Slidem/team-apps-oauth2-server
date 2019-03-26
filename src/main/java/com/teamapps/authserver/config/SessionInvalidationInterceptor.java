package com.teamapps.authserver.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

/**
 * Invalidates oauth2 session as soon as user enters credentials and is redirected to their page.
 * This forces the users to enter their credentials when they don't provide the authentication token.
 *
 * @author Mihai Alexandru
 * @date 23.03.2019
 */
public class SessionInvalidationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (nonNull(modelAndView) && modelAndView.getView() instanceof RedirectView) {
            RedirectView redirect = (RedirectView) modelAndView.getView();
            if (urlHasOneOf(redirect.getUrl(), "code=", "error=", "access_token")) {
                ofNullable(request.getSession(false)).ifPresent(HttpSession::invalidate);
            }
        }
    }

    private boolean urlHasOneOf(String redirectUrl, String... params) {
        if (isNull(redirectUrl)) {
            return false;
        }
        return stream(params).anyMatch(redirectUrl::contains);
    }
}
