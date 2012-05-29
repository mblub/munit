/**
 * Mule Development Kit
 * Copyright 2010-2011 (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.munit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Optional;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * <p>Module to test database connections</p>
 *
 * @author Federico, Fernando
 */
@Module(name="dbserver", schemaVersion="1.0")
public class DBServerModule
{
    /**
     * <p>JBDC url</p>
     */
    @Configurable
    private String database;

    /**
     * <p>Script to create the database.</p>
     */
    @Configurable
    @Optional
    private String creationalScript;

    /**
     * <p>Cvs files to create the tables in a JSON string</p>
     */
    @Configurable
    @Optional
    private String csv;


    private Connection connection;


    /**
     * <p>Start the server.</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:start}
     *
     */
    @Processor
    public void startDbServer()
    {
        try {

            addJdbcToClassLoader();
            connection = DriverManager.getConnection("jdbc:h2:mem:"+ database);
            Statement stmt = connection.createStatement();
            createTablesFromExpressions(stmt);
            createTablesFromCsv(stmt);

        } catch (Exception e) {
            throw new RuntimeException("Could not start the database server", e);
        }
    }

    private void createTablesFromCsv(Statement stmt) {
        if ( csv != null )
        {
            String[] tables = csv.split(";");
            for ( String table : tables )
            {
                String tableName = table.replaceAll(".csv", "");
                try {
                    stmt.execute("CREATE TABLE "+tableName+" AS SELECT * FROM CSVREAD(\'" + getClass().getResource("/"+table).toURI().toASCIIString()  + "\');");
                } catch (SQLException e) {
                    throw new RuntimeException("Invalid SQL, could not create table " + tableName + " from " + table);
                } catch (URISyntaxException e) {
                    throw new RuntimeException("Could not read file " + table);
                }
            }
        }
    }

    private void createTablesFromExpressions(Statement stmt) throws SQLException {
        if ( creationalScript != null )
        {
            String[] expressions = creationalScript.split(";");
            for ( String expression : expressions)
            {
                stmt.execute(expression);
            }
        }
    }

    /**
     * <p>Executes a SQL query</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:execute}
     *
     * @param sql query to be executed
     * @return result of the SQL query.
     */
    @Processor
    public Object execute(String sql)
    {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * <p>Executes a SQL query</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:executeQuery}
     *
     * @param sql query to be executed
     * @return result of the SQL query in a JSON format.
     */
    @Processor
    public Object executeQuery(String sql) {
        Statement statement = null;
        try {
            return getMap(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    private List<Map<String, String>> getMap(String sql) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
       List<Map<String, String>> jsonArray = new ArrayList<Map<String,String>>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            HashMap<String, String> jsonObject = new HashMap<String,String>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                jsonObject.put(columnName, String.valueOf(resultSet.getObject(columnName)));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    
    private JSONArray getJSON(String sql) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        JSONArray jsonArray = new JSONArray();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                jsonObject.put(columnName, resultSet.getObject(columnName));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * <p>Executes a SQL query</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:validateThat}
     *
     * @param query query to be executed
     * @param returns Expected value
     */
    @Processor
    public void validateThat(String query, String returns) {

        try {
            JSONArray jsonArray = getJSON(query);
            JSONArray parser = (JSONArray) new JSONParser().parse(returns);

            assertEquals(jsonArray, parser);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid JSON Object");
        }
        catch (ClassCastException ccException)
        {
            throw new RuntimeException("The JSON String must always be an array");
        } catch (SQLException e) {
            throw new RuntimeException("Invalid Query");
        }

    }

    /**
     * <p>Stops the server.</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:stop}
     */
    @Processor
    public void stopDbServer() {
        try {
            if ( connection != null ) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not stop the database server", e);
        }
    }

    private void addJdbcToClassLoader() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Class.forName("org.h2.Driver").newInstance();
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setCreationalScript(String creationalScript) {
        this.creationalScript = creationalScript;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }
}
