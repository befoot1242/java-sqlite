package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        // SQLite データベースファイルのパスを指定
        String url = "jdbc:sqlite:test.db";

        // データベースに接続して簡単な操作を行う
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

            String dropTable = "drop table students";
            stmt.execute(dropTable);

            // テーブルの作成
            String createTableSql = "";
            createTableSql += "CREATE TABLE IF NOT EXISTS students";
            createTableSql += "(";
            createTableSql += "id INTEGER PRIMARY KEY,";
            createTableSql += "name TEXT";
            createTableSql += ")";
            stmt.execute(createTableSql);

            // データの挿入
            String insertSql = "INSERT INTO students (name) VALUES ('John Doe')";
            stmt.execute(insertSql);
            insertSql = "INSERT INTO students (name) VALUES ('test1')";
            stmt.execute(insertSql);
            insertSql = "INSERT INTO students (name) VALUES ('test2')";
            stmt.execute(insertSql);

            // データの取得
            String selectSql = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(selectSql);

            // 取得したデータの表示
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
