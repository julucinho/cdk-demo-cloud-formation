package com.myorg.stacks;

import com.myorg.resources.RdsResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.Stack;
import software.constructs.Construct;

import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_RDS_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RdsStack extends Stack {

    private String thisStackId;

    public static RdsStack createUsing(Construct scope, VpcStack vpcStack){
        String thisStackId = DEMO_AWS_CLOUD_FORMATION_RDS_NAME.concat("-STACK");
        RdsStack thisStack = new RdsStack(scope, thisStackId, vpcStack);
        thisStack.addDependency(vpcStack);
        return thisStack;
    }

    public RdsStack(Construct scope, String thisStackId, VpcStack vpcStack){
        super(scope, thisStackId, null);
        this.thisStackId = thisStackId;
        RdsResource.createFor(this, vpcStack);
    }

}
