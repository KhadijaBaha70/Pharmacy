package sample;

import DbOutils.Dbconnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Example.fxml"));
        primaryStage.setScene(new Scene(root));
        // set borderless
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // drag ability
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.show();
    }

    public static void main(String[] args) throws ParseException, SQLException {
        /* JdbcOracleConnection jc = new JdbcOracleConnection();
        jc.connection();
        launch(args);*/
        try {
            Connection connection = Dbconnection.getConnection();
            String sql = "INSERT INTO empal(user_name) values('test3')";
            Statement stat = connection.createStatement();
            stat.executeUpdate(sql);
            stat.close();
            connection.close();
        } catch (Exception e) {
        }
        MEDICAMENT med = new MEDICAMENT();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String dt="2023-06-12";
        Date date = df.parse(dt);
        //med.insert("Dol",date,15.33,20,"description","DCI","forme","15mg",1);
        //med.delete("name3");
        boolean wt;
        //System.out.println(med.found("Dol"));
    }
}