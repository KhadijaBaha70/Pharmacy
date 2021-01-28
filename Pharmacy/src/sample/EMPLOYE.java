package sample;

import DbOutils.Dbconnection;

import java.sql.*;
import java.util.Date;

public class EMPLOYE {

    private Connection connection = null;

    public EMPLOYE(){
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

    public void insert(String empname, String prenom, Date daten,String sexe, Double salaire, Date datent, Date datesor,Integer phid) throws Exception{
        try {
            Connection connection = Dbconnection.getConnection();
            Statement stat = connection.createStatement();
            String sql = "INSERT INTO EMPLOYE(EMPL_FNAME,EMPL_LNAME,EMPL_BDAY,EMPL_SEX,EMPL_SALARY,EMPL_START,EMPL_END,PHAR_ID) values(?,?,?,?,?,?,?,?)";
            PreparedStatement prst = connection.prepareStatement(sql);
            prst = connection.prepareStatement(sql);
            prst.setString(1, empname);
            prst.setString(2,prenom);
            java.sql.Date Df1 = new java.sql.Date(daten.getDate());
            prst.setDate(3, Df1);
            prst.setString(4, sexe);
            prst.setDouble(5, salaire);
            java.sql.Date Df2 = new java.sql.Date(datent.getDate());
            prst.setDate(6, Df2);
            java.sql.Date Df3 = new java.sql.Date(datesor.getDate());
            prst.setDate(7, Df3);
            prst.setInt(8, phid);
            int insermed = prst.executeUpdate();
            System.out.println("Inserted " + insermed);
            prst.close();
            stat.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Could not insert data to database" + e.getMessage());
        }
    }
    public void delete(String Prenom, String nom) throws Exception {
        try {
            Connection connectiond = Dbconnection.getConnection();

            Statement statd = connectiond.createStatement();
            String sqld = "DELETE FROM EMPLOYE WHERE EMPL_FNAME = ? AND EMPL_LNAME = ?";
            PreparedStatement prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, Prenom);
            prst.setString(2,nom);
            int deletemed = prst.executeUpdate();
            System.out.println("deleted " + deletemed);
            prst.close();
            statd.close();
            connectiond.close();
        }catch(Exception e) {
            System.out.println("Could not delete data from database" + e.getMessage());
        }
    }
    public boolean found(String name, String Prenom) throws SQLException {
        PreparedStatement prst=null;
        ResultSet rs=null;

        try {
            Connection connectiond = Dbconnection.getConnection();
            Statement statd = connectiond.createStatement();
            String sqld = "SELECT * FROM EMPLOYE WHERE EMPL_LNAME = ? AND EMPL_FNAME=?";
            prst = connection.prepareStatement(sqld);
            prst = connectiond.prepareStatement(sqld);
            prst.setString(1, name);
            prst.setString(2,Prenom);
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
    public void update(String new_value) {
        try {
            Connection connectiond = Dbconnection.getConnection();

            Statement statd = connectiond.createStatement();
            String sqld = "UPDATE EMPLOYE SET column_name = ?";
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
