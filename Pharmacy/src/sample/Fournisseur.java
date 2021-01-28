package sample;

import DbOutils.Dbconnection;

import java.sql.*;
import java.util.Date;

public class Fournisseur {
    private Connection connection = null;
    public Fournisseur(){
        try {
            this.connection = Dbconnection.getConnection();
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isdatabaseconnected() {
        return this.connection != null;

    }
    public void insert(String name, String adresse, String tel, String email,String raiSoc,String civil) throws Exception{
        try {
            Connection connection = Dbconnection.getConnection();
            Statement stat = connection.createStatement();
            String sql = "INSERT INTO SUPPLIER (SUPL_NAME,SUPL_ADRESS,SUPL_TEL,SUPL_EMAIL,SUPL_RS,SUPL_CIVIL_ID) values(?,?,?,?,?,?)";
            PreparedStatement prst = connection.prepareStatement(sql);
            prst = connection.prepareStatement(sql);
            prst.setString(1, name);
            prst.setString(2,adresse);
            prst.setString(3, tel);
            prst.setString(4, email);
            prst.setString(5, raiSoc);
            prst.setString(6, civil);
            int insermed = prst.executeUpdate();
            System.out.println("Inserted " + insermed);
            prst.close();
            stat.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Could not insert data to database" + e.getMessage());
        }
    }
    public void delete(String civil) throws Exception {
        try {
            Connection connectiond = Dbconnection.getConnection();

            Statement statd = connectiond.createStatement();
            String sqld = "DELETE FROM SUPPLIER WHERE SUPL_CIVIL_ID = ? ";
            PreparedStatement prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, civil);
            int deletemed = prst.executeUpdate();
            System.out.println("deleted " + deletemed);
            prst.close();
            statd.close();
            connectiond.close();
        }catch(Exception e) {
            System.out.println("Could not delete data from database" + e.getMessage());
        }
    }
    public boolean found(String civil) throws SQLException {
        PreparedStatement prst=null;
        ResultSet rs=null;

        try {
            Connection connectiond = Dbconnection.getConnection();
            Statement statd = connectiond.createStatement();
            String sqld = "SELECT * FROM SUPPLIER WHERE SUPL_CIVIL_ID = ? ";
            prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, civil);
            rs=prst.executeQuery();
            if(rs.next()){
                System.out.println(rs);
                return true;
            }
            return false;

        }catch(Exception e) {
            return false;
        }
        finally{
            {
                prst.close();
                rs.close();
            }
        }
    }
}
