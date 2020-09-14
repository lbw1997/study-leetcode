package com.project.jvm.designpattern;

public class Skyrocket implements RocketSim{

    public double simTime;

    private double burnArea;
    private double burnRate;
    private double fuelMass;
    private double totalMass;

    public Skyrocket(double burnArea, double burnRate, double fuelMass, double totalMass) {
        this.burnArea = burnArea;
        this.burnRate = burnRate;
        this.fuelMass = fuelMass;
        this.totalMass = totalMass;
    }

    @Override
    public double getMass() {
        return 0;
    }

    @Override
    public double getThrust() {
        return 0;
    }

    @Override
    public void setSimTime(double time) {

    }
}

