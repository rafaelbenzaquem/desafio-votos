package br.com.benzaquem.desafiovotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioVotosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioVotosApplication.class, args);
    }

}
