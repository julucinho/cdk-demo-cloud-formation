package com.myorg;

import software.amazon.awscdk.App;

public class CdkApp {

    public static void main(final String[] args) {
        App app = new App();
        VpcStack.createUsing(app);
        app.synth();
    }

}

