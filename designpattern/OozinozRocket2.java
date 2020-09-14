package com.project.jvm.designpattern;

public class OozinozRocket2 extends Skyrocket{

    private PhysicalRocket physicalRocket;

    public OozinozRocket2(PhysicalRocket r) {
        super(
                r.burnArea,
                r.burnRate,
                r.fuelMass,
                r.totalMass);
        physicalRocket = r;
    }

    @Override
    public double getMass() {
        return physicalRocket.getMass(simTime);
    }

    @Override
    public double getThrust() {
        return physicalRocket.getThrust(simTime);
    }
}
