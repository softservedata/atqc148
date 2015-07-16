package com.softserve.edu.oms.data;

import com.softserve.edu.atqc.tools.DataSource;

public final class DataSourceRepository {

    private DataSourceRepository() {
    }

    public static DataSource getJtdsMsSqlCredentials() {
        return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
                "jdbc:jtds:sqlserver://ssu-sql12/Lv117proba;instance=tc;",
                "lv-117", "lv-117");
        		
//        		"jdbc:jtds:sqlserver://Admin-PC/136oms;instance=SQLEXPRESS;",
//              "db", "db");
    }

    public static DataSource getJtdsMsSqlSsuOms() {
        return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
                "jdbc:jtds:sqlserver://ssu-sql12/ssu-oms;instance=tc;",
                "ssu-oms", "ssu-oms");
    }
    // TODO Read from properties

}
