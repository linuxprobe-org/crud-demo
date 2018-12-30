package org.linuxprobe.demo;

import javax.validation.Valid;

import org.linuxprobe.demo.model.Book;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan({ "org.linuxprobe.**.mapper" })
@ComponentScan("org.linuxprobe.demo**")
@RestController
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/test")
	public String test(@Valid Book book) {
		return "jhe";
	}
}
