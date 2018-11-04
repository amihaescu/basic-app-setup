package ro.amihaescu.polls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;
import ro.amihaescu.polls.model.Role;
import ro.amihaescu.polls.repository.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static ro.amihaescu.polls.model.RoleName.ROLE_ADMIN;
import static ro.amihaescu.polls.model.RoleName.ROLE_USER;

@SpringBootApplication
@EntityScan(
		basePackageClasses = {
				PollsApplication.class,
				Jsr310Converters.class
		}
)
public class PollsApplication {

	@Autowired
	private RoleRepository roleRepository;

	@PostConstruct
	void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PollsApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			roleRepository.save(new Role().withName(ROLE_USER));
			roleRepository.save(new Role().withName(ROLE_ADMIN));
		};
	}*/
}
