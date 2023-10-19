package com.springbootmvcwithentity.demo.WebSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * Cấu hình Cors (Cross-Origin Resource Sharing):
 * Nếu ứng dụng của bạn chạy trên một tên miền khác với tên miền mà bạn đã đặt cookie,
 * bạn cần đảm bảo rằng cấu hình Cors cho phép truy cập cross-origin cookies.
 *
 * */

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*"); // Cho phép truy cập từ tất cả các nguồn (cần được cấu hình cụ thể trong môi trường sản xuất)
        corsConfiguration.addAllowedHeader("*"); // Cho phép tất cả các loại headers
        corsConfiguration.addAllowedMethod("*"); // Cho phép tất cả các loại HTTP methods

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
