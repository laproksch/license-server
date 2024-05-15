package at.fh.lp.licenseserver;

import at.fh.lp.licenseserver.entity.License;
import at.fh.lp.licenseserver.service.LicenseService;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class LicenseServerApplication {


	@Autowired
	private LicenseService licenseService;

	public static void main(String[] args) {
		SpringApplication.run(LicenseServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			log.info("Adding demo license data to DB");
			licenseService.createLicense(
				new License("Client 1", "xxxx", new Date(), new Date(), true, true));
			licenseService.createLicense(
				new License("Client 2", "yyyy", new Date(), new Date(), true, true));
			licenseService.createLicense(
				new License("Client 3", "zzzz", new Date(), new Date(), false, true));
		};
	}
}
