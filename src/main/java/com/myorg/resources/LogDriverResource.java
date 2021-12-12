package com.myorg.resources;

import com.myorg.stacks.DemoAwsCloudFormationServiceStack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.ecs.AwsLogDriverProps;
import software.amazon.awscdk.services.ecs.LogDriver;
import software.amazon.awscdk.services.logs.ILogGroup;
import software.amazon.awscdk.services.logs.LogGroup;

import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_LOG_DRIVER_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogDriverResource {


    public static LogDriver createFor(DemoAwsCloudFormationServiceStack demoAwsCloudFormationServiceStack) {
        return LogDriver.awsLogs(buildLogDriverPropsForLogDriverOf(demoAwsCloudFormationServiceStack));
    }

    private static AwsLogDriverProps buildLogDriverPropsForLogDriverOf(DemoAwsCloudFormationServiceStack demoAwsCloudFormationServiceStack) {
        return AwsLogDriverProps.builder()
                .logGroup(buildLogGroupForLogDriverUsing(demoAwsCloudFormationServiceStack))
                .streamPrefix(DEMO_AWS_CLOUD_FORMATION_LOG_DRIVER_NAME)
                .build();
    }

    private static ILogGroup buildLogGroupForLogDriverUsing(DemoAwsCloudFormationServiceStack demoAwsCloudFormationServiceStack) {
        return LogGroup.Builder.create(demoAwsCloudFormationServiceStack, DEMO_AWS_CLOUD_FORMATION_LOG_DRIVER_NAME)
                .logGroupName(DEMO_AWS_CLOUD_FORMATION_LOG_DRIVER_NAME)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();
    }
}
