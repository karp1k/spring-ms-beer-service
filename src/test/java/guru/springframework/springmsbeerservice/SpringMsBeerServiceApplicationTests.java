package guru.springframework.springmsbeerservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false") // esacping errors - 'No session' on shutdown
class SpringMsBeerServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
