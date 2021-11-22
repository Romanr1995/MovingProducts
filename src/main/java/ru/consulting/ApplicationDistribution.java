package ru.consulting;

import java.util.Map;

public class ApplicationDistribution {

    public static void main(String[] args) {
        DistributionSystem distributionSystem = new DistributionSystem();
        distributionSystem.downloadFileTxtAndConvertToWarehouses(args[0]);
        distributionSystem.printWarehousesWithProducts();
    }
}
