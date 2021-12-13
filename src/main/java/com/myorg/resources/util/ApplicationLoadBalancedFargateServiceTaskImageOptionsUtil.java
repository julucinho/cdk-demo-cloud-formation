package com.myorg.resources.util;

import com.myorg.resources.LogDriverResource;
import com.myorg.stacks.DemoAwsCloudFormationServiceStack;
import lombok.experimental.UtilityClass;
import software.amazon.awscdk.Fn;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;

import java.util.HashMap;
import java.util.Map;

import static com.myorg.constants.AppConstants.DatabaseConfigs.*;
import static com.myorg.constants.AppConstants.Defaults.DEFAULT_PORT;
import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_CONTAINER_NAME;

@UtilityClass
public class ApplicationLoadBalancedFargateServiceTaskImageOptionsUtil {

    private static final String DEMO_AWS_CLOUD_FORMATION_CONTAINER_IMAGE_URL = "julucin/demo-aws-cloud-formation:0.0.2";

    public static ApplicationLoadBalancedTaskImageOptions createFor(DemoAwsCloudFormationServiceStack demoAwsCloudFormationServiceStack) {
        return ApplicationLoadBalancedTaskImageOptions.builder()
                .image(ContainerImage.fromRegistry(DEMO_AWS_CLOUD_FORMATION_CONTAINER_IMAGE_URL))
                .containerName(DEMO_AWS_CLOUD_FORMATION_CONTAINER_NAME)
                .containerPort(DEFAULT_PORT)
                .logDriver(LogDriverResource.createFor(demoAwsCloudFormationServiceStack))
                .environment(buildEnvironmentVariables())
                .build();
    }

    private static Map<String, String> buildEnvironmentVariables(){
        Map<String, String> environmentVariables = new HashMap<>();
        environmentVariables.put(SPRING_DATASOURCE_URL_KEY_NAME, buildDsUrlValue());
        environmentVariables.put(SPRING_DATASOURCE_USERNAME_KEY_NAME, DB_USERNAME);
        environmentVariables.put(SPRING_DATASOURCE_PASS_KEY_NAME, Fn.importValue(RDS_PASS_KEY_NAME));
        return environmentVariables;
    }

    private static String buildDsUrlValue() {
        return "jdbc:mariadb://"
                .concat(Fn.importValue(RDS_ENDPOINT_KEY_NAME))
                .concat(":")
                .concat(DB_PORT.toString())
                .concat("/")
                .concat(SCHEMA_NAME)
                .concat("?createDatabaseIfNotExist=true");
    }

}
