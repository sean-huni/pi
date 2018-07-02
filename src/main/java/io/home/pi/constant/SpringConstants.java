package io.home.pi.constant;

import java.util.Arrays;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi
 * USER      : sean
 * DATE      : 13-June-2018
 * TIME      : 01:37
 */
public class SpringConstants {
    public static final String HOME_PAGE_TITLE = "Pi Home";
    public static final String PROFILE_PAGE_TITLE = "User Profile";

    public static final String PI = "/pi/**";
    public static final String AUTHORITY_USER = "USER";

    public static final String USER_ROLE_PREFIX = "ROLE_";
    public static final String URL_LOGIN_PAGE = "/user/login";
    public static final String URL_REG_LISTENER = "/registrationConfirm";
    public static final String URL_REG_NEW_USER = "/register/user";
    public static final String URL_FORGOT_PASS = "/user/forgot-pass";
    public static final String URL_DEFAULT_AUTH_USER_PAGE = "/pi/dashboard";
    public static final String URL_INVALID_SESSION = "/user/login";
    public static final String URL_LOGOUT = "/j_spring_security_logout";
    public static final String URL_LOGOUT_SUCCESSFUL = "/user/login?logout=true";
    public static final String COOKIES_SESSION = "JSESSIONID";
    public static final String KEY_REMEMBER_ME = "uniqueAndSecret";
    public static final String PARAM_PASS_FIELD = "passLoginTxtBox";
    public static final String COOKIE_REMEMBER_ME = "vhiT0WjOaEVBeoXxvrTuTxQEWYHZVn0eafjYl3atd8dhp9T";

    private static final String URL_RESOURCES_CSS = "css/**";
    private static final String URL_RESOURCES_PUB = "public/**";
    private static final String URL_RESOURCES_STATIC = "static/**";
    private static final String URL_RESOURCES_WEBJAR = "/webjars/**";
    private static final String URL_RESOURCES_H2 = "/h2";
    private static final String URL_RESOURCES_ROOT = "/";

    private static final String URL_INTERNAL_RESOURCES_PUB = "classpath:/public/";
    private static final String URL_INTERNAL_RESOURCES_STATIC = "classpath:/static/";
    private static final String URL_INTERNAL_RESOURCES_WEBJAR = "classpath:/META-INF/resources/webjars/";

    public static final String[] EXTERNAL_URL_RESOURCES = {URL_RESOURCES_CSS, URL_RESOURCES_PUB,
            URL_RESOURCES_STATIC, URL_RESOURCES_WEBJAR, URL_RESOURCES_H2, URL_RESOURCES_H2 + "**",
            URL_RESOURCES_ROOT};

    public static final String[] INTERNAL_URL_RESOURCES = {
            URL_INTERNAL_RESOURCES_PUB, URL_INTERNAL_RESOURCES_STATIC, URL_INTERNAL_RESOURCES_WEBJAR};

    public static final String SECURITY_PERMIT_ALL_URLS = Arrays.toString(
            new String[]{URL_REG_LISTENER, URL_FORGOT_PASS, URL_REG_NEW_USER})
            .replace("[", "").replace("]", "");

    public static final String URL_LOGIN_ERROR_TRUE = "/user/login?error=true";

}
