package nl.pingwins.competition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import ratpack.spring.config.EnableRatpack;

@SpringBootApplication
@EnableRatpack
@EnableCaching
public class CompetitionCalendarImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetitionCalendarImporterApplication.class, args);
	}
}
