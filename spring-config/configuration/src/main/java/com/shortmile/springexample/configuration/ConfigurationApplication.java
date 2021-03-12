package com.shortmile.springexample.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * Spring configutation
 * ====================
 * https://www.youtube.com/watch?v=PsNNGuLi0ns&t=288s (Josh Lang: Spring Tips: Configuration)
 * <p>
 * 1. Environment env object can be injected to any bean and then you can use: env.getProperty("property.name").
 * 2. By default properties are read from application.properites or application.yml, but this can be changes.
 * - program argument: --spring.config.name=foo  # this will now search for application.properties in the path.
 * 3. Using spring profiles: --spring.profiles.active=dev  (in idea: there is a separate tool in the run window).
 * - the application-dev.propereties is then overriding the default propereties.
 * - it is possible to also use both config name and profile.
 * - the properties with the same names from active profile will override the generic propereties
 * - if there are many profiles active for example: --spring.profiles.active=dev,dev2 then the last one wins if
 * properties are overriden.
 * - there is also an application-default.properties that will be used when no profiles are active (higher priority then
 * normal application.properties).
 * - you can specify default null for injected @Value property with: @Value("${greetings-message:#{null}}")
 */
@Log4j2
@EnableConfigurationProperties(BootifulProperties.class)
@SpringBootApplication
public class ConfigurationApplication {

    public static void main(String[] args) {
//	    SpringApplication.run(ConfigurationApplication.class, args);
        new SpringApplicationBuilder()
                .sources(ConfigurationApplication.class)
//                .initializers(applicationContext -> {
//                    applicationContext.getEnvironment().getPropertySources().addLast(new BootyfulPropertySource());
//                })
                .build()
                .run(args);

    }

    //Injected configurable source may not work every time as the initialization is already made.
    @Autowired
    void contributeToPropSources(ConfigurableEnvironment env) {
        env.getPropertySources().addLast(new BootyfulPropertySource());
    }
     
    @Bean
    ApplicationRunner applicationRunner(Environment environment,
                                        @Value("${HOME}") String userHome,
                                        @Value("${spring.datasource.url}") String datasourceUrl,
                                        // can be passed as prog arg: --message-from-program-args="helllo from prog. args." 
                                        @Value("${message-from-program-args:}") String messageFromProgramArgs,
                                        @Value("${greetings-message:Default message}") String defaultValue,
                                        @Value("${bootyful-message}") String bootyfulMessage,
                                        //inject properties class annotated with @ConfiguationProperties
                                        BootifulProperties bootifulProperties, 
                                        @Value("${message-from-config-server}") String configServerMessage
    ) { 
        return args -> {
            log.info("message forma application.properties " + environment.getProperty("message-from-application-properties"));
            log.info("default value " + defaultValue);
            log.info("user.home " + userHome);
            log.info("message from program args " + messageFromProgramArgs);
            log.info("datasource " + datasourceUrl);
            log.info("message from custom property source " + bootyfulMessage);
            //System.getenv().entrySet().forEach(System.out::println);
            log.info("message from @ConfigurationProperties " + bootifulProperties.getMessage());
            log.info("message from Spring Cloud Config Server " + configServerMessage);
        };
    }


    static class BootyfulPropertySource extends PropertySource<String> {
        BootyfulPropertySource() {
            super("bootyful");
        }

        @Override
        public Object getProperty(String name) {
            if (name.equalsIgnoreCase("bootyful-message")) {
                return "hello from " + BootyfulPropertySource.class.getSimpleName() + "!";
            }
            return null;
        }
    }
    
}

