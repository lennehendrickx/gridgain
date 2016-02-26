package be.lennehendrickx.main;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.gridgain.grid.configuration.GridGainConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@ComponentScan
public class TestConfiguration {

    public static final String GRID_NAME = "lennegrid";

    @Bean
    public GridGainConfiguration gridGainConfiguration() {
        GridGainConfiguration configuration = new GridGainConfiguration();
        configuration.setRollingUpdatesEnabled(true);
        return configuration;
    }
        @Bean
    public DiscoverySpi tcpDiscoverySpi() {
        TcpDiscoverySpi discoSpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(singletonList("127.0.0.1:47500..47509"));
        discoSpi.setIpFinder(ipFinder);
        return discoSpi;
    }

    @Bean
    public IgniteConfiguration igniteConfiguration(GridGainConfiguration gridGainConfiguration, DiscoverySpi discoverySpi, List<LifecycleBean> lifecycleBeans) {
        IgniteConfiguration configuration = new IgniteConfiguration();
        configuration.setGridName(GRID_NAME);
        configuration.setPluginConfigurations(gridGainConfiguration);
        configuration.setDiscoverySpi(discoverySpi);
        configuration.setLifecycleBeans(lifecycleBeans.toArray(new LifecycleBean[lifecycleBeans.size()]));
        return configuration;
    }
}
