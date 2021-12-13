package com.myorg.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    @UtilityClass
    public static class ResourceNames{
        public static final String DEMO_AWS_CLOUD_FORMATION_SERVICE_NAME = "DEMO-AWS-CLOUD-FORMATION-SERVICE";
        public static final String DEMO_AWS_CLOUD_FORMATION_VPC_NAME = "DEMO-AWS-CLOUD-FORMATION-VPC";
        public static final String DEMO_AWS_CLOUD_FORMATION_ECS_CLUSTER_NAME = "DEMO-AWS-CLOUD-FORMATION-ECS-CLUSTER";
        public static final String DEMO_AWS_CLOUD_FORMATION_CONTAINER_NAME = "DEMO-AWS-CLOUD-FORMATION-CONTAINER";
        public static final String DEMO_AWS_CLOUD_FORMATION_LOG_DRIVER_NAME = "DEMO-AWS-CLOUD-FORMATION-LOG-DRIVER";
        public static final String DEMO_AWS_CLOUD_FORMATION_SERVICE_AUTOSCALING_NAME = "DEMO-AWS-CLOUD-FORMATION-SERVICE-AUTOSCALING-NAME";
        public static final String DEMO_AWS_CLOUD_FORMATION_RDS_NAME = "DEMO-AWS-CLOUD-FORMATION-RDS";
        public static final String DEMO_AWS_CLOUD_FORMATION_RDS_INSTANCE_NAME = "DEMO-AWS-CLOUD-FORMATION-RDS-INSTANCE";
    }

    @UtilityClass
    public static class DatabaseConfigs{
        public static final String DB_USERNAME = "admin";
        public static final String RDS_ENDPOINT_KEY_NAME = "rds-endpoint";
        public static final String RDS_PASS_KEY_NAME = "rds-pass";
        public static final String SPRING_DATASOURCE_URL_KEY_NAME = "SPRING_DATASOURCE_URL";
        public static final Number DB_PORT = 3306;
        public static final String SCHEMA_NAME = "demo_aws_cloud_formation_schema";
        public static final String SPRING_DATASOURCE_USERNAME_KEY_NAME = "SPRING_DATASOURCE_USERNAME";
        public static final String SPRING_DATASOURCE_PASS_KEY_NAME = "SPRING_DATASOURCE_PASSWORD";
    }

    @UtilityClass
    public static class Defaults{
        public static final Number DEFAULT_PORT = 8080;
        public static final Number DEFAULT_MIN_CAPACITY = 2;
        public static final Number DEFAULT_MAX_CAPACITY = 4;
    }
}