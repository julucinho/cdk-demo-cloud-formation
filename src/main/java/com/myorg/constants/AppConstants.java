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
    }

    @UtilityClass
    public static class Defaults{
        public static final Number DEFAULT_PORT = 8080;
        public static final Number DEFAULT_MIN_CAPACITY = 2;
        public static final Number DEFAULT_MAX_CAPACITY = 4;
    }
}