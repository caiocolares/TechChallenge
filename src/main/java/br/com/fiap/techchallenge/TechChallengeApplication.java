package br.com.fiap.techchallenge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TechChallengeApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(TechChallengeApplication.class)

                .run(args);
    }

}
