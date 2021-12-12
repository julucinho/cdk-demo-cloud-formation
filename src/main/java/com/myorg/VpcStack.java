package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

public class VpcStack extends Stack {

    static void createUsing(final Construct scope){
        new VpcStack(scope);
    }

    private VpcStack(final Construct scope){
        super(scope, "VPC-DEMO-AWS-CLOUD-FORMATION-STACK", null);
        Vpc.Builder.create(this, "VPC-DEMO-AWS-CLOUD-FORMATION")
                .maxAzs(3)
                .build();
    }

}
