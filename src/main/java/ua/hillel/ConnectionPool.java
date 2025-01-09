//package ua.hillel;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//public class ConnectionPool {
//    private static HikariConfig config = new HikariConfig();
//    static {
//        config.setJdbcUrl("jdbc:postgresql://localhost:5432/company");
//        config.setUsername("postgres");
//        config.setPassword("1212");
//    }
//    private static HikariDataSource ds = new HikariDataSource(config);
//
//    private ConnectionPool() {}
//
//    public static HikariDataSource getDataSource() {
//        return ds;
//    }
//}
