package org.example;

public class DBRouterBase {
    private String tbIdx;
    public String getTbIdx() {
        return DBContextHolder.getTBKey();
    }
}
