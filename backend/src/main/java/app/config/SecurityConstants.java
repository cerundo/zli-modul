package app.config;

public final class SecurityConstants {
    public static final String JWT_SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 1200000; // 20 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_LOGIN_URL = "/users/sign-up";
}