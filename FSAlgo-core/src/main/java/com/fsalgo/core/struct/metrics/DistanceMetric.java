package com.fsalgo.core.struct.metrics;

/**
 * @Author: root
 * @Date: 2023/3/6 20:53
 * @Description:
 */
public interface DistanceMetric extends GeometricalDistance{

    double DEFAULT_P = 2.0;

    double getP();
}