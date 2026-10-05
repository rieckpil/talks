package pragmatech.digital.conference;

import org.springframework.boot.SpringApplication;

public class TestPromptItRightApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
