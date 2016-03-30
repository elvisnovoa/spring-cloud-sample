package com.sample.cloud.accounts.config;

import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Profile;

@Profile("cloud-enabled")
@SpringCloudApplication
@EnableZuulProxy
@EnableHystrix
public class CloudConfig {

}
