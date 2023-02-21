package com.realblox.dimetime.config;

import com.realblox.dimetime.interceptor.CertificationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*
     * 로그인 인증 Interceptor 설정
     * */
    @Autowired
    CertificationInterceptor certificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(certificationInterceptor)
//                .excludePathPatterns("/account/**")
//                .excludePathPatterns("/battle/**")
//                .excludePathPatterns("/card/**")
//                .excludePathPatterns("/season/**")
//                .excludePathPatterns("/product/**");
    }

    /**
     * 로컬디렉토리 직접 다운로드 가능하도록 가상패스 설정
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //NFT 가상경로
//        registry.addResourceHandler("/nft/**")
//                .addResourceLocations("file:/usr/local/nft/");

        //NFT 가상경로
        registry.addResourceHandler("/nftimg/**")
                .addResourceLocations("file:/usr/local/nft/");

        //업로드 파일 가상경로
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:///" + uploadPath);

    }

}
