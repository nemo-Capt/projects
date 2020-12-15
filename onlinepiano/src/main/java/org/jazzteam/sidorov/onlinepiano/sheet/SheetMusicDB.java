package org.jazzteam.sidorov.onlinepiano.sheet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SheetMusicDB {

    private static String url = "jdbc:mysql://localhost/sheetmusicdb?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "root";
    private static String password = "root";

    public static ArrayList<SheetMusic> select() {

        ArrayList<SheetMusic> products = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    double barSize = resultSet.getDouble(2);
                    NoteName noteName = NoteName.valueOf(resultSet.getString(3));
                    String name = resultSet.getString(4);
                    double tempo = resultSet.getDouble(5);
                    List<Note> noteList = resultSet.getArray(6);

                    SheetMusic sheetMusic = new SheetMusic(id, barSize, noteName, name, tempo);
                    products.add(sheetMusic);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return products;
    }
    public static SheetMusic selectOne(int id) {

        SheetMusic sheetMusic = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT * FROM products WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int prodId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        int price = resultSet.getInt(3);
                        int amount = resultSet.getInt(4);
                        sheetMusic = new SheetMusic(prodId, name, price, amount);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return sheetMusic;
    }
    public static int insert(SheetMusic sheetMusic) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO products (name, price, amount) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, sheetMusic.getName());
                    preparedStatement.setInt(2, sheetMusic.getPrice());
                    preparedStatement.setInt(3, sheetMusic.getAmount());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(SheetMusic sheetMusic) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE products SET name = ?, price = ?, amount = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, sheetMusic.getName());
                    preparedStatement.setInt(2, sheetMusic.getPrice());
                    preparedStatement.setInt(3, sheetMusic.getAmount());
                    preparedStatement.setInt(4, sheetMusic.getId());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static int delete(int id) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM products WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
}
