package cn.com.zjw.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class SpringbootApplication extends WebMvcConfigurationSupport {

    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        // setUseSuffixPatternMatch设置是否是后缀模式匹配,如“/user”是否匹配/user.*，true即匹配
        // setUseTrailingSlashMatch设置是否自动后缀路径模式匹配,如"/user"是否匹配"/user/",默认真即匹配
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
