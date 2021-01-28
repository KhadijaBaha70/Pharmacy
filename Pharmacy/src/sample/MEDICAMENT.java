package sample;

import DbOutils.Dbconnection;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MEDICAMENT {


    private Connection connection = null;

    public MEDICAMENT() {
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

    public void insert(String medname, Date date, Double P, Integer qte, String descript, String dci, String forme, String dos, Integer gp) throws Exception {
        try {
            Connection connection = Dbconnection.getConnection();
            Statement stat = connection.createStatement();
            String sql = "INSERT INTO MEDICINE(MEDI_NAME,MEDI_EXPIRE_DATE_,MEDI_PU,MEDI_STOCK_QTE,MEDI_DESC,MEDI_DCI,MEDI_FORM,MEDI_DOSE) values(?,?,?,?,?,?,?,?)";
            PreparedStatement prst = connection.prepareStatement(sql);
            prst = connection.prepareStatement(sql);
            prst.setString(1, medname);
            java.sql.Date Df = new java.sql.Date(date.getDate());
            prst.setDate(2, Df);
            prst.setDouble(3, P);
            prst.setInt(4, qte);
            prst.setString(5, descript);
            prst.setString(6, dci);
            prst.setString(7, forme);
            prst.setString(8, dos);
            int insermed = prst.executeUpdate();
            System.out.println("Inserted " + insermed);
            prst.close();
            stat.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Could not insert data to database" + e.getMessage());
        }
    }

    public void delete(String name) throws Exception {
        try {
            Connection connectiond = Dbconnection.getConnection();

            Statement statd = connectiond.createStatement();
            String sqld = "DELETE FROM MEDICINE WHERE MEDI_NAME = ?";
            PreparedStatement prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, name);
            int deletemed = prst.executeUpdate();
            System.out.println("deleted " + deletemed);
            prst.close();
            statd.close();
            connectiond.close();
        }catch(Exception e) {
            System.out.println("Could not delete data from database" + e.getMessage());
        }
    }
    public boolean found(String name) throws SQLException {
        PreparedStatement prst=null;
        ResultSet rs=null;

        try {
            Connection connectiond = Dbconnection.getConnection();
            Statement statd = connectiond.createStatement();
            String sqld = "SELECT * FROM MEDICINE WHERE MEDI_NAME = ?";
            prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, name);
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
    public void update(String new_value) throws Exception {
        try {
            Connection connectiond = Dbconnection.getConnection();

            Statement statd = connectiond.createStatement();
            String sqld = "UPDATE MEDICINE SET column_name = ?";
            PreparedStatement prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, new_value);
            int deletemed = prst.executeUpdate();
            System.out.println("updated" + deletemed);
            prst.close();
            statd.close();
            connectiond.close();
        }catch(Exception e) {
            System.out.println("Could not update data" + e.getMessage());
        }
    }
}