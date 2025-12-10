package com.latptop.flexuy.ConfigSecurity;

import jakarta.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String servletPath = request.getServletPath();
        return url.substring(0, url.length() - servletPath.length());
    }
}
