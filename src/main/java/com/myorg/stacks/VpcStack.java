package com.myorg.stacks;

import com.myorg.resources.VpcResource;
import lombok.Getter;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_VPC_NAME;

@Getter
public class VpcStack extends Stack {

    private final Vpc vpc;

    public static VpcStack createUsing(Construct scope){
        String stackId = DEMO_AWS_CLOUD_FORMATION_VPC_NAME.concat("-STACK");
        return new VpcStack(scope, stackId);
    }

    private VpcStack(Construct scope, String stackId){
        super(scope, stackId, null);
        this.vpc = VpcResource.createFor(this);
    }

}
