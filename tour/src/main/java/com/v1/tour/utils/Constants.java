package com.v1.tour.utils;

public class Constants {
    private Constants() {
    }

    public static class UrlPath {
        private UrlPath() {
        }

        public static final String AUTH = "auth";
        public static final String ROLE = "role";
    }

    public static class ErrorType {
        private ErrorType() {
        }

        public static final String UNAUTHORIZED = "UNAUTHORIZED";
        public static final String BAD_REQUEST = "BAD_REQUEST";
        public static final String ACCESS_DENIED = "ACCESS_DENIED";
        public static final String INVALID_ERROR = "INVALID_ERROR";

        public static final String EXPIRED_JWT = "EXPIRED_JWT";
        public static final String INVALID_JWT = "INVALID_JWT";
        public static final String REFRESH_TOKEN_NOT_FOUND = "REFRESH_TOKEN_NOT_FOUND";
        public static final String REFRESH_TOKEN_EXPIRED = "REFRESH_TOKEN_EXPIRED";

        public static final String FIELD_NULL = "FIELD_NULL";
        public static final String FIELD_BLANK = "FIELD_BLANK";

        public static final String ID_NOT_FOUND = "ID_NOT_FOUND";
        public static final String EMAIL_NOT_FOUND = "EMAIL_NOT_FOUND";
        public static final String INVALID_EMAIL = "INVALID_EMAIL";
    }
}
