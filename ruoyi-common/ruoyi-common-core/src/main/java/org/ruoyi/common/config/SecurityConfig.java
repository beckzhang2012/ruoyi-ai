package org.ruoyi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全配置类
 * 
 * @author ageerle
 * @date 2025-11-10
 */
@Configuration
public class SecurityConfig {

    /**
     * 配置BCryptPasswordEncoder的Bean
     * 
     * @return BCryptPasswordEncoder的Bean
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
