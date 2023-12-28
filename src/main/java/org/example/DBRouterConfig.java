package org.example;

public class DBRouterConfig {
    private int dbConut;
    private int tbConut;

    public DBRouterConfig(int dbConut, int tbConut) {
        this.dbConut = dbConut;
        this.tbConut = tbConut;
    }

    public DBRouterConfig() {
    }

    public int getDbConut() {
        return dbConut;
    }

    public void setDbConut(int dbConut) {
        this.dbConut = dbConut;
    }

    public int getTbConut() {
        return tbConut;
    }

    public void setTbConut(int tbConut) {
        this.tbConut = tbConut;
    }
}
