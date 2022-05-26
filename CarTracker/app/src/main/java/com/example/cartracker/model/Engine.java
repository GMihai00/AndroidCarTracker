package com.example.cartracker.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

public class Engine implements Comparable<Engine>, Serializable {

    public static String[] codes;
    public static String[] oils;
    public static String[] distributions;
    public static Integer[] oilLongevity;
    public static Integer[] distributionLongevity;

    final static  Integer[] capacitys =
            new Integer[]{1000, 1200, 1400, 1600, 1800, 2000, 2200, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000};
    final static Integer[] price =
            new Integer[]{40, 48, 56, 64, 180, 200, 869, 948, 1027, 2226, 2385, 5120, 5440, 5760, 6080, 6400};

    final static TreeMap<Integer, Integer> mapedPrices = new TreeMap<>();

    final static TreeMap<String, String> mapedOils = new TreeMap<>();


    final static TreeMap<String, String> mapedDistributions = new TreeMap<>();

    private static TreeMap<String, Integer> oilLongevitys = new TreeMap<>();

    private static TreeMap<String, Integer> distributionLongevitys = new TreeMap<>();

    private String code;
    private Integer cubicCapacity;
    private String oil;
    private String distribution;
    private Date oilExpireDate;
    private Date distributionExpireDate;

    public Engine(
            String code)
    {
        OnObjectCreate();
        this.code = code;
        int cubicCapacity = Character.getNumericValue(code.charAt(0)) * 10 + Character.getNumericValue(code.charAt(2));
        cubicCapacity = cubicCapacity * 100;
        this.cubicCapacity = cubicCapacity;
        this.oil = getOilBasedOnEngineCode(code);
        this.distribution = getDistributionBasedOnEngineCode(code);
    }

    void OnObjectCreate()
    {
        for(int i = 0; i < capacitys.length; i++)
        {
            mapedPrices.put(capacitys[i], price[i]);
        }

        for(int i = 0; i < codes.length; i++)
        {
            mapedOils.put(codes[i], oils[i]);
        }

        for(int i = 0; i < codes.length; i++)
        {
            mapedDistributions.put(codes[i], distributions[i]);
        }

        for(int i = 0; i < codes.length; i++)
        {
            distributionLongevitys.put(codes[i], distributionLongevity[i]);
        }

        for(int i = 0; i < codes.length; i++)
        {
            oilLongevitys.put(codes[i], oilLongevity[i]);
        }
    }
    public static Integer getTaxBasedOnCubicCapacity(Integer cubicCapacity)
    {
        Integer rez = mapedPrices.floorEntry(cubicCapacity).getValue();

        return rez;
    }

    public static String getOilBasedOnEngineCode(String code)
    {
        return mapedOils.get(code);
    }

    public static String getDistributionBasedOnEngineCode(String code)
    {
        return mapedDistributions.get(code);
    }

    public Integer getTaxes()
    {
        return getTaxBasedOnCubicCapacity(cubicCapacity);
    }

    public String getCode() {
        return code;
    }

    public Integer getCubicCapacity()
    {
        return cubicCapacity;
    }
    public String getOil() {
        return oil;
    }

    public String getDistribution() {
        return distribution;
    }

    public Date getOilExpireDate() {
        return oilExpireDate;
    }

    public Date getDistributionExpireDate() {
        return distributionExpireDate;
    }

    public static Date addMonths(Date today, int monthsToAdd) {
        if (today != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.MONTH, monthsToAdd);

            return c.getTime();
        } else {
            return null;
        }
    }

    public void resetOilExpireDate() {
        Date oilExpireDate = addMonths(new Date(), oilLongevitys.get(code));

        this.oilExpireDate = oilExpireDate;
    }

    public void resetDistributionExpireDate() {
        Date distributionExpireDate = addMonths(new Date(), distributionLongevitys.get(code));

        this.distributionExpireDate = distributionExpireDate;
    }

    @Override
    public int compareTo(Engine engine) {
        return  ((code == engine.code) &&
                (oil == engine.oil) &&
                (distribution == engine.distribution)) ?
                0
                :
                code.compareTo(engine.code);
    }
}
