package com.jhb0430.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jhb0430.memo.common.FileManager;
import com.jhb0430.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
							//window 경로면 ///로. 이외에는 //
		registry.addResourceHandler("/images/**")	
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/"); // 경로가 수정되는 경우 - > 다 바꾸기 번거로움 
//		.addResourceLocations("file:///" + "D:\\jh240729\\spring project\\upload\\memo/" );
	}
	
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		
		registry.addInterceptor(new PermissionInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/logout","/static/**","/images/**");
		
	}
	
}


