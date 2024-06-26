package studio.jact.gamebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "studio.jact.gamebox.profit_pioneer.server.*"))
public class ProfitPioneerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfitPioneerApplication.class, args);
    }

}
