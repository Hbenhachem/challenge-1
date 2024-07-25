package com.baridmedia.configuration;




public class SecurityConfig {
       private static final  String[] PUBLIC_PATHS_LIST = new String[]{
            "/api/v1/authentication/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console/**"
    };

}