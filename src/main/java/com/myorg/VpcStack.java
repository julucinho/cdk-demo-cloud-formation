package com.myorg;

import lombok.Getter;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

@Getter
public class VpcStack extends Stack {

    private final Vpc vpc;

    static VpcStack createUsing(final Construct scope){
        return new VpcStack(scope);
    }

    private VpcStack(final Construct scope){
        super(scope, "VPC-DEMO-AWS-CLOUD-FORMATION-STACK", null);
        this.vpc = Vpc.Builder.create(this, "VPC-DEMO-AWS-CLOUD-FORMATION")
                .maxAzs(3)
                .build();
    }

}
