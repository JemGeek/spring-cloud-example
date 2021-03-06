package com.xingtuai.cloud.nacos.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author James
 * @date 2020/9/27
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosDiscoveryConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryConsumerApplication.class, args);
    }
}
