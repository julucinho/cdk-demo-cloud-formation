package com.myorg.stacks;

import com.myorg.resources.ApplicationLoadBalancedFargateServiceResource;
import com.myorg.resources.HealthCheckResource;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.applicationautoscaling.EnableScalingProps;
import software.amazon.awscdk.services.ecs.CpuUtilizationScalingProps;
import software.amazon.awscdk.services.ecs.ScalableTaskCount;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.constructs.Construct;

import static com.myorg.constants.AppConstants.Defaults.DEFAULT_MAX_CAPACITY;
import static com.myorg.constants.AppConstants.Defaults.DEFAULT_MIN_CAPACITY;
import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_SERVICE_AUTOSCALING_NAME;
import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_SERVICE_NAME;

public class DemoAwsCloudFormationServiceStack extends Stack {


    public static void createUsing(Construct scope, EcsStack ecsStack, RdsStack rdsStack) {
        String thisStackId = DEMO_AWS_CLOUD_FORMATION_SERVICE_NAME.concat("-STACK");
        DemoAwsCloudFormationServiceStack thisStack = new DemoAwsCloudFormationServiceStack(scope, thisStackId, ecsStack);
        thisStack.addDependency(ecsStack);
        thisStack.addDependency(rdsStack);
    }

    public DemoAwsCloudFormationServiceStack(Construct scope, String thisStackId, EcsStack ecsStack) {
        super(scope, thisStackId);
        ApplicationLoadBalancedFargateService service = ApplicationLoadBalancedFargateServiceResource.createFor(this, ecsStack);
        service.getTargetGroup().configureHealthCheck(HealthCheckResource.create());
        setAutoscalingFor(service);
    }

    private void setAutoscalingFor(ApplicationLoadBalancedFargateService service) {
        ScalableTaskCount scalableTaskCount = service.getService()
                .autoScaleTaskCount(EnableScalingProps.builder()
                        .minCapacity(DEFAULT_MIN_CAPACITY)
                        .maxCapacity(DEFAULT_MAX_CAPACITY)
                        .build());
        scalableTaskCount.scaleOnCpuUtilization(DEMO_AWS_CLOUD_FORMATION_SERVICE_AUTOSCALING_NAME, CpuUtilizationScalingProps.builder()
                        .targetUtilizationPercent(50)
                        .scaleInCooldown(Duration.seconds(60))
                        .scaleOutCooldown(Duration.seconds(60))
                        .build());
    }

}
