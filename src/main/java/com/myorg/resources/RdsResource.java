package com.myorg.resources;

import com.myorg.stacks.RdsStack;
import com.myorg.stacks.VpcStack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.CfnParameter;
import software.amazon.awscdk.SecretValue;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.rds.*;

import java.util.Collections;

import static com.myorg.constants.AppConstants.DatabaseConfigs.*;
import static com.myorg.constants.AppConstants.ResourceNames.DEMO_AWS_CLOUD_FORMATION_RDS_INSTANCE_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RdsResource {

    private CfnParameter dbPassReceivedAsParam;
    private ISecurityGroup iSecurityGroup;
    private DatabaseInstance databaseInstance;

    public static void createFor(RdsStack rdsStack, VpcStack vpcStack) {
        RdsResource rdsResource = new RdsResource();
        rdsResource.receiveDbPassAsParamIn(rdsStack);
        rdsResource.configureSecurityGroupUsing(rdsStack, vpcStack);
        rdsResource.buildDatabaseInstanceUsing(rdsStack, vpcStack);
        rdsResource.exposeRdsEndpointUsing(rdsStack);
        rdsResource.exposePassReceivedUsing(rdsStack);
    }

    private void receiveDbPassAsParamIn(RdsStack rdsStack) {
        this.dbPassReceivedAsParam = CfnParameter.Builder.create(rdsStack, "dbPass")
                .type("String")
                .description("Credential for using the database")
                .build();
    }

    private void configureSecurityGroupUsing(RdsStack rdsStack, VpcStack vpcStack) {
        this.iSecurityGroup = SecurityGroup.fromSecurityGroupId(rdsStack, rdsStack.getThisStackId(), vpcStack.getVpc().getVpcDefaultSecurityGroup());
        this.iSecurityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(DB_PORT));
    }

    private void buildDatabaseInstanceUsing(RdsStack rdsStack, VpcStack vpcStack) {
        this.databaseInstance = DatabaseInstance.Builder.create(rdsStack, DEMO_AWS_CLOUD_FORMATION_RDS_INSTANCE_NAME)
                .instanceIdentifier(DEMO_AWS_CLOUD_FORMATION_RDS_INSTANCE_NAME.concat("01"))
                .engine(DatabaseInstanceEngine.mysql(MySqlInstanceEngineProps.builder().version(MysqlEngineVersion.VER_5_7).build()))
                .vpc(vpcStack.getVpc())
                .credentials(this.buildCredentialsForDataBaseInstance())
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.MICRO))
                .multiAz(false)
                .allocatedStorage(10)
                .securityGroups(Collections.singletonList(this.iSecurityGroup))
                .vpcSubnets(SubnetSelection.builder().subnets(vpcStack.getVpc().getPrivateSubnets()).build())
                .build();
    }

    private Credentials buildCredentialsForDataBaseInstance() {
        return Credentials.fromUsername(DB_USERNAME, CredentialsFromUsernameOptions.builder()
                .password(SecretValue.plainText(this.dbPassReceivedAsParam.getValueAsString()))
                .build());
    }

    private void exposeRdsEndpointUsing(RdsStack rdsStack) {
        CfnOutput.Builder.create(rdsStack, RDS_ENDPOINT_KEY_NAME)
                .exportName(RDS_ENDPOINT_KEY_NAME)
                .value(this.databaseInstance.getDbInstanceEndpointAddress())
                .build();
    }

    private void exposePassReceivedUsing(RdsStack rdsStack) {
        CfnOutput.Builder.create(rdsStack, RDS_PASS_KEY_NAME)
                .exportName(RDS_PASS_KEY_NAME)
                .value(this.dbPassReceivedAsParam.getValueAsString())
                .build();
    }

}
