package com.vnit.api.util;

import java.util.*;
import java.sql.*;
import java.io.*;
import com.vnit.api.entity.Object;

public class dbUtility {

    private ArrayList<Object> columns =  new ArrayList<>();
    private Set<String> primaryKeyColumns = new HashSet<>();
        
    public void fillMap(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;    
            String currentMap = "";

            while ((line = reader.readLine()) != null) {
                if(line.startsWith("#")) {
                    currentMap = line.substring(1).trim();
                }
                else {
                    String []str = line.split("=");
                    if(currentMap.equals("Mysql server details")) {
                        mapsUtil.dbDetailMap.put(str[0], str[1]);
                    }
                    else 
                    if(currentMap.equals("Java data type details")) {
                        mapsUtil.variableMap.put(str[0], str[1]);
                    }
                    else 
                    if(currentMap.equals("Constants details")) {
                        mapsUtil.constantsMap.put(str[0], str[1]);
                    }
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public Connection createDBConnection() {
        String url = getDBDetailMapping("db_url");
        String username = getDBDetailMapping("db_username");
        String password = getDBDetailMapping("db_password");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    
    public ArrayList<Object> getColumns(String tableName, Connection connection) {        
        try {

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columnDetails = metaData.getColumns(null, null, tableName, null);

            //get primary ket details
            getPrimaryKeyColumns(metaData, tableName);

            // Process and print the column details
            while (columnDetails.next()) {
                String columnName = columnDetails.getString("COLUMN_NAME");
                String columnType = columnDetails.getString("TYPE_NAME");
                boolean isPrimaryKey = primaryKeyColumns.contains(columnName);
                int columnSize = columnDetails.getInt("COLUMN_SIZE");
                boolean required = (columnDetails.getString("IS_NULLABLE").equals("YES")) ? true : false; 
                
                columnType = getDBVariableMapping(columnType);

                Object o = new Object(columnName, columnType, isPrimaryKey, columnSize, required);
                columns.add(o);

                Map<String, String> fld_map = new HashMap<>();
                fld_map.put("column_name", columnName);
                fld_map.put("column_type", columnType);
                fld_map.put("primary_key", isPrimaryKey ? "true" : "false");
                fld_map.put("size", String.valueOf(columnSize));
                fld_map.put("required", required ? "true" : "false");

                mapsUtil.fld.put(columnName, fld_map);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return columns;
    }

    public Set<String> getPrimaryKeyColumns(DatabaseMetaData metaData, String tableName) {    
        try {
            ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName);

            while (primaryKeysResultSet.next()) {
                String primaryKeyColumnName = primaryKeysResultSet.getString("COLUMN_NAME");
                primaryKeyColumns.add(primaryKeyColumnName);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return primaryKeyColumns;
    }

    public String getPrimaryKeyColumn() {
        ArrayList<String> list = new ArrayList<>(primaryKeyColumns);
        return list.get(0);
    }

    public String getDBVariableMapping(String dbVariable) {
        return mapsUtil.variableMap.get(dbVariable);
    }

    public String getDBDetailMapping(String dbDetail) {
        return mapsUtil.dbDetailMap.get(dbDetail);
    }

    public String getConstantDetailMapping(String constant) {
        return mapsUtil.constantsMap.get(constant);
    }
}
