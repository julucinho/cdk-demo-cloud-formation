package com.myorg.resources.util;

import com.myorg.resources.LogDriverResource;
import com.myorg.stacks.DemoAwsCloudFormationServiceStack;
import lombok.experimental.UtilityClass;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;

import static com.myorg.constants.AppConstants.Defaults.DEFAULT_PORT;
import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_CONTAINER_NAME;

@UtilityClass
public class ApplicationLoadBalancedFargateServiceTaskImageOptionsUtil {

    private static final String DEMO_AWS_CLOUD_FORMATION_CONTAINER_IMAGE_URL = "julucin/demo-aws-cloud-formation:latest";

    public static ApplicationLoadBalancedTaskImageOptions createFor(DemoAwsCloudFormationServiceStack demoAwsCloudFormationServiceStack) {
        return ApplicationLoadBalancedTaskImageOptions.builder()
                .image(ContainerImage.fromRegistry(DEMO_AWS_CLOUD_FORMATION_CONTAINER_IMAGE_URL))
                .containerName(DEMO_AWS_CLOUD_FORMATION_CONTAINER_NAME)
                .containerPort(DEFAULT_PORT)
                .logDriver(LogDriverResource.createFor(demoAwsCloudFormationServiceStack))
                .build();
    }
}
