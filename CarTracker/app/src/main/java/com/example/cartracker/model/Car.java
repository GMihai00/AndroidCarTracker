package com.example.cartracker.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class Car implements Comparable<Car>, Serializable {

    private static AtomicLong nextId = new AtomicLong();
    private Long id;
    protected CarMake make;
    protected String model;
    protected Engine engine;
    protected Vector<MandatoryInsurance> insurances;

    public Car(CarMake make, Engine engine, String model) {
        id = nextId.incrementAndGet();
        this.make = make;
        this.engine = engine;
        this.model = model;
        insurances = new Vector<MandatoryInsurance>();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMake(CarMake make) {
        this.make = make;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarMake getMake() {
        return make;
    }

    public Engine getEngine() {
        return engine;
    }

    public Integer getTaxes() { return engine.getTaxes();}

    public Vector<MandatoryInsurance> getInsurances()
    {
        return  insurances;
    }
    public Date getOilExpireDate(){ return engine.getOilExpireDate();}

    public Date getDistributionExpireDate(){ return engine.getDistributionExpireDate();}
    public Integer getCubicCapacity() {return engine.getCubicCapacity();}
    @Override
    public int compareTo(Car car) {
        return id.compareTo(car.id);
    }
}
