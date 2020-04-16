package am.nuaca.bim;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Autowired
	public Flyway flyway(DataSource dataSource) {
		FluentConfiguration configuration = new FluentConfiguration().dataSource(dataSource)
				.locations("classpath:db/migration");
		Flyway flyway = new Flyway(configuration);
		flyway.migrate();
		return flyway;
	}
}
