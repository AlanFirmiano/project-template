package com.template.apptemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class AppTemplateApplication

fun main(args: Array<String>) {
	runApplication<AppTemplateApplication>(*args)

	@Bean
	fun corsConfigurer(): WebMvcConfigurer {
		return object : WebMvcConfigurer {
			override fun addCorsMappings(registry: CorsRegistry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
						.allowedHeaders("*", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
								"Access-Control-Allow-Methods", "Access-Control-Allow-Credentials")
			}
		}
	}
}
