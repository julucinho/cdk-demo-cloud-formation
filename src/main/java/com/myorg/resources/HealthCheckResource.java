package com.myorg.resources;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.services.elasticloadbalancingv2.HealthCheck;

import static com.myorg.constants.AppConstants.Defaults.DEFAULT_PORT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HealthCheckResource {

    public static HealthCheck create() {
        return new HealthCheck.Builder()
                .path("/actuator/health")
                .port(DEFAULT_PORT.toString())
                .healthyHttpCodes("200")
                .build();
    }
}
