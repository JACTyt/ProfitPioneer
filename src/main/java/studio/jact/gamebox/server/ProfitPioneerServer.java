package studio.jact.gamebox.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import studio.jact.gamebox.game.profit_pioneer.core.JobExtras;
import studio.jact.gamebox.server.controller.ProfitPioneerController;

@SpringBootApplication
@Configuration
@EntityScan("studio.jact.gamebox.entity")
public class ProfitPioneerServer {
    public static void main(String[] args) {
        SpringApplication.run(ProfitPioneerServer.class, args);
    }

    @Bean
    public ProfitPioneerController profitPioneerController() {
        return new ProfitPioneerController();
    }

    @Bean
    public JobExtras jobExtras() {
        return new JobExtras();
    }
}
