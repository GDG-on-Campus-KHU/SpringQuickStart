package com.springbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 1. LocaleResolver (세션 기반) 등록
     * 사용자의 언어 정보를 Session에 저장하고 관리하도록 설정합니다.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();

        // 기본 로케일을 한국어(KOREAN)로 설정 (선택 사항)
        // new Locale("ko") 대신 Locale.KOREAN 을 사용해도 됩니다.
        sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);

        return sessionLocaleResolver;
    }

    /**
     * 2. LocaleChangeInterceptor 등록
     * URL 파라미터(예: ?lang=en)를 감지하여 Locale을 변경하는 인터셉터입니다.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();

        // 언어 변경을 감지할 파라미터 이름을 'lang'으로 설정합니다. (기본값도 lang)
        interceptor.setParamName("lang");

        return interceptor;
    }

    /**
     * 3. 인터셉터 적용
     * DispatcherServlet이 요청을 처리하기 전에 인터셉터가 동작하도록 등록합니다.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}