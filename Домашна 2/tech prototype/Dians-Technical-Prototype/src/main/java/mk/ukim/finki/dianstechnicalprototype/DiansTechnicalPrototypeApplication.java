package mk.ukim.finki.dianstechnicalprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DiansTechnicalPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiansTechnicalPrototypeApplication.class, args);
    }

}
