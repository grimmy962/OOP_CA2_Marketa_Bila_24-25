package org.example;

public class Box implements IMeasurableContainer{
    private double length, width, depth, weight;

    private Box(double length, double width, double depth, double weight){
        this.length = length;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
    }

    @Override
        public double weight(){
        return this.weight;
    }

    @Override
        public double rectangularVolume(){
        return this.length * this.width * this.depth;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public double getWeight() {
        return weight;
    }
}
