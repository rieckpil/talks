package pragmatech.digital.prompt_it_right;

import org.springframework.boot.SpringApplication;

public class TestPromptItRightApplication {

	public static void main(String[] args) {
		SpringApplication.from(PromptItRightApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
