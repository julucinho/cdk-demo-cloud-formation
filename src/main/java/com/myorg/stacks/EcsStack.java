package com.myorg.stacks;

import com.myorg.resources.EcsResource;
import lombok.Getter;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;
import software.constructs.Construct;

import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_ECS_CLUSTER_NAME;

@Getter
public class EcsStack extends Stack {

    private final Cluster cluster;

    public static EcsStack createUsing(final Construct scope, VpcStack vpcStack) {
        String clusterStackId = DEMO_AWS_CLOUD_FORMATION_ECS_CLUSTER_NAME.concat( "-STACK");
        EcsStack ecsStack = new EcsStack(scope, vpcStack.getVpc(), clusterStackId);
        ecsStack.addDependency(vpcStack);
        return ecsStack;
    }

    private EcsStack(Construct scope, Vpc vpc, String id){
        super(scope, id, null);
        this.cluster = EcsResource.createFor(this, vpc);
    }

}
