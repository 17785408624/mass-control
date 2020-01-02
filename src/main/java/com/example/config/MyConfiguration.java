package com.example.config;

import com.example.common.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.Executor;

/**
 *
 */
@EnableAsync
@Configuration
public class MyConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private AccessInterceptor accessInterceptor;

    /**
     * 开放跨域访问
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**");
            }
        };
    }

    //    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/myResource/");
//        super.addResourceHandlers(registry);
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(accessInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/user/reg").
                excludePathPatterns("/user/login/**");

    }

    //新增加一个类用来添加虚拟路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + ProjectConf.PROJECT_STATIC_PATH_FULL)
                .addResourceLocations("file:" + ProjectConf.UPLOAD_PATH_FULL)
        ;
    }

    /**
     * 多线程执行
     *
     * @return
     */
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }
}