package com.myorg.resources;

import com.myorg.stacks.VpcStack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.services.ec2.Vpc;

import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_VPC_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VpcResource {

    public static Vpc createFor(VpcStack vpcStack){
        return Vpc.Builder.create(vpcStack, DEMO_AWS_CLOUD_FORMATION_VPC_NAME)
                .maxAzs(3)
                .build();
    }

}
