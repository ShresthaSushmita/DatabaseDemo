package com.example.kanchha.databasedemo;

/**
 * Created by kanchha on 3/4/2017.
 */
public class DBConstant {
    final static public int DB_VERSION = 1;
    final static public String DB_NAME = "FIRST";
    final static public String TB_NAME = "Students";
    final static public String _ID = "id";
    final static public String ST_NAME = "Name";
    final static public String ST_ROLL = "Roll";
    final static public String CREATE_QUERY = "CREATE TABLE "+TB_NAME+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ST_NAME+" VARCHAR(255),"+
            ST_ROLL+" VARCHAR(10));";
    final static String DELETE_QUERY = "DELETE TABLE IF EXIST "+TB_NAME;
}