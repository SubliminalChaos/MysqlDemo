package org.waqe.pl.sqlproggy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

import static java.lang.System.exit;


public final class SqlProggy extends JavaPlugin {
    private Connection con;
    public String host, database, username, password, table;
    public int port;

    @Override
    public void onEnable() {

        System.out.println(">>>>>\nENABLED PLUGIN: " + this.toString());
        mysqlsetup();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `count` WHERE `age` = 2");
            while (rs.next())
                System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(con.toString());
            e.printStackTrace();
        }
    }

    private void mysqlsetup() {
        host = "localhost";
        database = "testdb";
        username = "root";
        password = "";
        table = "count";
        port = 3306;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://"+host+":"+port+"/"+database, username, password);
        } catch (SQLException e) {
            //System.out.println(con.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            con.close();
        } catch (SQLException e) {
            //System.out.println(con.toString());
            e.printStackTrace();
        }
    }
}

