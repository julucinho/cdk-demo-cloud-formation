package com.myorg;

import com.myorg.stacks.DemoAwsCloudFormationServiceStack;
import com.myorg.stacks.EcsStack;
import com.myorg.stacks.VpcStack;
import software.amazon.awscdk.App;

public class CdkApp {

    public static void main(final String[] args) {
        App app = new App();
        VpcStack vpcStack = VpcStack.createUsing(app);
        EcsStack ecsStack = EcsStack.createUsing(app, vpcStack);
        DemoAwsCloudFormationServiceStack.createUsing(app, ecsStack);
        app.synth();
    }

}

