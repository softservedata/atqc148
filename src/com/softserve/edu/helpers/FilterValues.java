package com.softserve.edu.helpers;

public enum FilterValues {

	Status ("Status", "OrderStatusRef");

    private final String name;
    private final String dbName;

    private FilterValues(String name, String dbName) {
        this.name = name;
        this.dbName = dbName;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getDbName() {
    	return this.dbName;
    }

    public String toString(){
       return name + "_" + dbName;
    }
}
