package com.project.jvm.designpattern;

class PhysicalRocket {
    public PhysicalRocket(double burnArea, double burnRate, double fuelMass, double totalMass) {
        this.burnArea = burnArea;
        this.burnRate = burnRate;
        this.fuelMass = fuelMass;
        this.totalMass = totalMass;
    }

    public double burnArea;
    public double burnRate;
    public double fuelMass;
    public double totalMass;

    public double getBurnTime() {
        return burnArea/burnRate;
    }

    public double getMass(double time) {
        if (time * burnRate < burnArea) {
            return totalMass-burnRate*time;
        }
        return totalMass - fuelMass;
    }

    public double getThrust(double time) {
        return fuelMass * burnRate * time;
    }
}
