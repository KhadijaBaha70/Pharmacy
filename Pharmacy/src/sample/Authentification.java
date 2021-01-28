package sample;


import DbOutils.Dbconnection;

import java.sql.*;

public class Authentification {
    JdbcOracleConnection b = new JdbcOracleConnection();
    Connection connection;



    public Authentification(){
        try{
            this.connection= Dbconnection.getConnection();
        }catch(SQLException ex){

            ex.printStackTrace();
        }
        if(this.connection==null)
        {
            System.exit(1);
        }
    }

    //cette partie du code permet d'etablir une connection avec la base de données

    public boolean isdatabaseconnected()
    {
        return this.connection!=null;

    }
    public boolean isLogin(String user,String password) throws Exception{
        b.connection();
        PreparedStatement pr =null;
        ResultSet rs = null;
        // hna bedlo la requete dyalkom
        String sql="SELECT * FROM AUTHENTIFICATION WHERE  USERNAME=? and PASSWORD=?  ";
        try{
            pr=this.connection.prepareStatement(sql);
            // hna f7ala ghadi yakhod l pam lwl dyal fonction o y3tih l username fi
            // la requete o l params tani o y3tih l password
            pr.setString(1, user);
            pr.setString(2, password);
            rs=pr.executeQuery();
            boolean bool1;
            if(rs.next()){
                // hna ila rs dyali kan 3amr o jab chi l3ba mn data base
                // daba rah ojdbc makantch dakhla sf dakhlnaha o la connection m3a
                // db oracle dazet mzn khedma n9iya
                // la classe Dbconnection zwina khedmo biha hiya li fiha la connection dyalkom nadya ok
                System.out.println(rs);
                return true;
            }
            return false;
        }catch(SQLException ex)
        {
            return false;
        }
        finally{
            {
                pr.close();
                rs.close();
            }
        }

    }

}