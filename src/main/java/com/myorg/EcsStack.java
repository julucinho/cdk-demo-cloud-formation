package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;
import software.constructs.Construct;

public class EcsStack extends Stack {

    static void createUsing(final Construct scope, VpcStack vpcStack) {
        String clusterStackId = "ECS-CLUSTER-DEMO-AWS-CLOUD-FORMATION-STACK";
        EcsStack ecsStack = new EcsStack(scope, vpcStack.getVpc(), clusterStackId);
        ecsStack.addDependency(vpcStack);
    }

    private EcsStack(Construct scope, Vpc vpc, String id){
        super(scope, id, null);
        Cluster.Builder.create(this, id)
                .clusterName("ECS-CLUSTER-DEMO-AWS-CLOUD-FORMATION")
                .vpc(vpc)
                .build();
    }

}
