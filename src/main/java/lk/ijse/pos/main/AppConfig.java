package lk.ijse.pos.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("lk.ijse.pos")
@Import(JpaConfig.class)
public class AppConfig {
}
