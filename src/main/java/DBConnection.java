import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Restaurant";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "31388";

    public static List<Blydo> dbUpdate() {
        List<Blydo> listBlyd = new ArrayList<>();

        Connection dbConnection = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            System.out.println("Creating statement...");
            stmt = dbConnection.createStatement();
            stmt1 = dbConnection.createStatement();
            stmt2 = dbConnection.createStatement();
            String sqlListBlyd;
            sqlListBlyd = "SELECT * FROM blydo";
            rs = stmt.executeQuery(sqlListBlyd);

            String sqlSpisokIdIngradientov;
            sqlSpisokIdIngradientov = "SELECT * FROM ingradienti_blyda";

            String sqlSpisokIngradientov;
            sqlSpisokIngradientov = "SELECT * FROM ingradient";

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("idBlydo");
                String nazvanie = rs.getString("nazvanie");
                String kategoriya = rs.getString("kategoriya");
                double stoimost = rs.getDouble("stoimost");
                double ves = rs.getDouble("ves");

                Blydo blydo = new Blydo(id, nazvanie, kategoriya, stoimost, ves);

                List<Integer> idIngradientovBlyda = new ArrayList<>();
                rs1 = stmt1.executeQuery(sqlSpisokIdIngradientov);
                while (rs1.next()) {
                    if (rs1.getInt("idBlyda") == id) {
                        idIngradientovBlyda.add(rs1.getInt("idIngradienta"));
                    }
                }

                if (!idIngradientovBlyda.isEmpty()) {
                    rs2 = stmt2.executeQuery(sqlSpisokIngradientov);
                    while (rs2.next()) {
                        for (Integer idIngradient : idIngradientovBlyda) {
                            if (rs2.getInt("idIngradient") == idIngradient) {
                                blydo.setSpisokIngradientov(blydo.getSpisokIngradientov()
                                        + rs2.getString("nazvanieIngradient") + ", ");
                            }
                        }
                    }
                    blydo.setSpisokIngradientov(blydo.getSpisokIngradientov().
                            substring(0, (blydo.getSpisokIngradientov().length() - 2)));
                }
                listBlyd.add(blydo);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs1 != null) {
                    rs1.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (stmt1 != null) {
                    stmt1.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return listBlyd;
    }

    public static List<Sotrudnik> getStaff() {
        List<Sotrudnik> listSotrudnikov = new ArrayList<>();

        Connection dbConnection = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Statement stmt3 = null;
        ResultSet rs3 = null;

        try {
            stmt3 = dbConnection.createStatement();
            String sqlSotrudniki = "SELECT * FROM sotrudnik";
            rs3 = stmt3.executeQuery(sqlSotrudniki);
            while (rs3.next()) {
                //Retrieve by column name
                String imya = rs3.getString("imya");
                Sotrudnik sotrudnik = new Sotrudnik(imya);
                listSotrudnikov.add(sotrudnik);
            }

        } catch (SQLException se) {//Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {//Handle errors for Class.forName
            e.printStackTrace();
        } finally {//finally block used to close resources
            try {
                if (rs3 != null) {
                    rs3.close();
                }
                if (stmt3 != null) {
                    stmt3.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return listSotrudnikov;
    }
}