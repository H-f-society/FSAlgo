package com.fsalgo.core.struct;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: root
 * @Date: 2022/12/16 21:55
 * @Description:
 */
public class Edge<N> implements Serializable {

    private static final long serialVersionUID = 6376080637258667838L;

    private N source;

    private N target;

    private double weight = 1.0;

    public Edge() {

    }

    public Edge(N source, N target) {
        this.source = source;
        this.target = target;
    }

    public Edge(N source, N target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public void setSource(N source) {
        this.source = source;
    }

    public void setTarget(N target) {
        this.target = target;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public N getSource() {
        return source;
    }

    public N getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Edge<?> edge = (Edge<?>) obj;
        return Objects.equals(source, edge.getSource()) && Objects.equals(target, edge.getTarget()) && (weight == edge.getWeight());
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + target + " : " + weight + ")";
    }
}
