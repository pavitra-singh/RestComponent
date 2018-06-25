package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages ={"com.myValuePack", "com.myValuePack.endpoint"})
public class MvcConfiguration implements WebMvcConfigurer{
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	 
	/**
	 * Bean to convert the response
	 * object to JSON
	 * @return
	 */
	@Bean
    public MappingJackson2JsonView jsonViewResolver(){
		MappingJackson2JsonView mappingJacksonViewResolver = new MappingJackson2JsonView();
		mappingJacksonViewResolver.setPrefixJson(true);
		return mappingJacksonViewResolver;
    }
}
