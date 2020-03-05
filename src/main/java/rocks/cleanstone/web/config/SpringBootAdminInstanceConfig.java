package rocks.cleanstone.web.config;

import lombok.Data;

@Data
public class SpringBootAdminInstanceConfig {
    private boolean enabled;
    private String url;
}