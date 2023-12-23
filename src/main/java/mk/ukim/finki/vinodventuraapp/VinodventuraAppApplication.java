package mk.ukim.finki.vinodventuraapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class VinodventuraAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VinodventuraAppApplication.class, args);
    }

}
