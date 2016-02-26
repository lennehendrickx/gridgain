package be.lennehendrickx.main;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestApplication {

    public static void main(String[] args) throws Exception {
        try {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);
            IgniteConfiguration configuration = ctx.getBean(IgniteConfiguration.class);
            Ignite ignite = Ignition.start(configuration);
            UUID id = ignite.cluster().localNode().id();
            ignite.compute().broadcast((IgniteRunnable) () ->
                    System.out.println(">>> Node with id=[" + id + "] has entered the network at " + LocalDateTime.now())
            );
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}