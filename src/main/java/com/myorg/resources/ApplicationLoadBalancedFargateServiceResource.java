package com.myorg.resources;

import com.myorg.resources.util.ApplicationLoadBalancedFargateServiceTaskImageOptionsUtil;
import com.myorg.stacks.DemoAwsCloudFormationServiceStack;
import com.myorg.stacks.EcsStack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;

import static com.myorg.constants.AppConstants.Defaults.DEFAULT_PORT;
import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_SERVICE_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationLoadBalancedFargateServiceResource {

    public static ApplicationLoadBalancedFargateService createFor(DemoAwsCloudFormationServiceStack demoAwsCloudFormationServiceStack, EcsStack ecsStack) {
        String serviceId = "LOAD-BALANCED-".concat(DEMO_AWS_CLOUD_FORMATION_SERVICE_NAME);
        return ApplicationLoadBalancedFargateService.Builder
                .create(demoAwsCloudFormationServiceStack, serviceId)
                .serviceName(DEMO_AWS_CLOUD_FORMATION_SERVICE_NAME)
                .cluster(ecsStack.getCluster())
                .cpu(512)
                .memoryLimitMiB(1024)
                .desiredCount(2)
                .listenerPort(DEFAULT_PORT)
                .taskImageOptions(ApplicationLoadBalancedFargateServiceTaskImageOptionsUtil.createFor(demoAwsCloudFormationServiceStack))
                .publicLoadBalancer(true)
                .build();
    }
}
