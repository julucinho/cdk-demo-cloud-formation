package com.myorg.resources;

import com.myorg.stacks.EcsStack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;

import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_ECS_CLUSTER_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EcsResource {

    public static Cluster createFor(EcsStack ecsStack, Vpc vpc) {
        return Cluster.Builder.create(ecsStack, DEMO_AWS_CLOUD_FORMATION_ECS_CLUSTER_NAME)
                .clusterName(DEMO_AWS_CLOUD_FORMATION_ECS_CLUSTER_NAME)
                .vpc(vpc)
                .build();
    }
}
