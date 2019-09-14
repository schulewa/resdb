package com.apschulewitz.resdb.common.model.utils;

import com.apschulewitz.resdb.common.ResearchDatabaseModelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Types.*;

@Component
@Slf4j
public class JdbcUtils {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUser;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.driver}")
    private String databaseDriver;


    /**
     * Method <CODE>executeScript</CODE> is part of the public api.
     * @param conn a java.sql.Connection used to execute the sql commands
     * @param scriptName a String denoting the script to be executed
     * @return a JdbcResponse holding the result of processing the request
     * @see JdbcResponse
     */
    public JdbcResponse executeScript(Connection conn, JdbcActionType jdbcActionType, String scriptName) {
        String sql = readScript(scriptName);
        log.debug("Executing sql: {}", sql);
        switch (jdbcActionType) {
            case Delete:
                return executeDelete(conn, readScript(scriptName));
            case Insert:
                return executeInsert(conn, readScript(scriptName));
            case Update:
                return executeUpdate(conn, readScript(scriptName));
            case Select:
                return executeSelect(conn, scriptName);
        }
        return new JdbcResponse();
    }

    /**
     * Method <CODE>executeScript</CODE> is part of the public api.
     * @param conn a java.sql.Connection used to execute the sql commands
     * @param request a JdbcRequest denoting the request to be executed
     * @return a JdbcResponse signifying the number of rows affected
     */
    public JdbcResponse execute(Connection conn, JdbcRequest request) {
        return executeScript(conn, request.getType(), request.scriptName);
    }

    public static List<JdbcResponse> executeScripts(List<JdbcRequest> requests) {
        JdbcUtils utils = new JdbcUtils();
        return executeScripts(utils.getConnection().get(), requests);
    }

    public static List<JdbcResponse> executeScripts(Connection connection, List<JdbcRequest> requests) {
        JdbcUtils utils = new JdbcUtils();
        return requests.stream()
                        .peek(r -> log.debug("Processing request - type {} script {}", r.getType().name(), r.scriptName))
                        .map(req -> utils.execute(connection, req))
                        .collect(Collectors.toList());
    }

    public Optional<Connection> getConnection() {
        return getConnection(databaseUrl, databaseUser, databasePassword);
    }

    public static Optional<Connection> getConnection(String dbUrl, String dbUser, String dbPassword) {
        Optional<Connection> connection;
        try {
            connection = Optional.of(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        } catch (SQLException sqle) {
            String errMsg = "Error initialising database connection: " + sqle.getMessage();
            log.error(errMsg, sqle);
            throw new ResearchDatabaseModelException(errMsg);
        }
        return connection;
    }

//    public JdbcResponse executeScript(JdbcActionType jdbcActionType, String scriptName) {
//        return executeScript(getConnection().get(), jdbcActionType, scriptName);
//    }

    private JdbcResponse executeInsert(Connection connection, String sql) {
        Statement stmt = null;

        try {
           stmt = createStatement(connection);
           int rowsAffected = stmt.executeUpdate(sql);
            return new JdbcResponse(rowsAffected);
        } catch (SQLException e) {
            String errMsg = "Error executing sql: " + e.getMessage();
            log.error(errMsg, e);
            return new JdbcResponse(new ResearchDatabaseModelException(errMsg, e));
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        }

    }

    private JdbcResponse executeDelete(Connection connection, String sql) {
        return new JdbcResponse(3); // TODO
    }

    private JdbcResponse executeUpdate(Connection connection, String sql) {
        return new JdbcResponse(2); // TODO
    }

    private JdbcResponse executeSelect(Connection connection, String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, Object> record;
        List<Map<String, Object>> someData = new ArrayList<>();
        Long bigIntValue = null;
        BigDecimal bigDecimal = null;
        try {
            stmt = createStatement(connection);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            final int columnCount = meta.getColumnCount();
            java.sql.Date sqlDate = null;
            LocalDate localDate = null;
            Double doubleV = null;
            int colType;
            while (rs.next()) {
                record = new HashMap<>();
                for (int colNo = 0; colNo < columnCount; colNo++) {

                    switch (meta.getColumnType(colNo)) {
                        case BIGINT:
                            bigIntValue = rs.getLong(colNo);
                            record.put(meta.getColumnName(colNo), bigIntValue);
                            break;
                        case CHAR:
                        case VARCHAR:
                            record.put(meta.getColumnName(colNo), rs.getString(colNo));
                            break;
                        case DATE:
                            sqlDate = rs.getDate(colNo);
                            if (sqlDate != null) {
                                localDate = sqlDate.toLocalDate();
                            }
                            record.put(meta.getColumnName(colNo), localDate);
                            break;
                        case DECIMAL:
                            record.put(meta.getColumnName(colNo), rs.getBigDecimal(colNo));
                            break;
                        case DOUBLE:
                        case FLOAT:
                            record.put(meta.getColumnName(colNo), rs.getDouble(colNo));
                            break;
                        case INTEGER:
                            record.put(meta.getColumnName(colNo), rs.getInt(colNo));
                            break;
                    }
                }
                someData.add(record);
            }
            return new JdbcResponse(someData);
        } catch (SQLException e) {
            String errMsg = "Error executing sql: " + e.getMessage();
            log.error(errMsg, e);
            return new JdbcResponse(new ResearchDatabaseModelException(errMsg, e));
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        }
//        return new JdbcResponse(someData); // TODO
    }

    private Statement createStatement(Connection conn) {
        try {
            return conn.createStatement();
        } catch (SQLException sqle) {
            String errMsg = "Error creating sql statement: " + sqle.getMessage();
            log.error(errMsg, sqle);
            throw new ResearchDatabaseModelException(errMsg, sqle);
        }
    }

    private String readScript(String scriptName) {
        try (InputStream stream = new ClassPathResource(scriptName).getInputStream()) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            String errMsg = "Error reading script " + scriptName + ": " + ioe.getMessage();
            log.error(errMsg);
            throw new ResearchDatabaseModelException(errMsg, ioe);
        }
    }

    public enum JdbcActionType {
        Insert, Update, Delete, Select
    }

    public final class JdbcRequest {
        private final JdbcActionType type;
        private final String scriptName;

        public JdbcRequest(final JdbcActionType type, final String scriptName) {
            this.type = type;
            this.scriptName = scriptName;
        }

        public JdbcActionType getType() {
            return type;
        }

        public String getScriptName() {
            return scriptName;
        }
    }

    /**
     * <P><CODE>JdbcResponse</CODE> holds the result from attempting to execute a sql script.<BR/>
     * This will contain the following methods:
     * <ul>
     *     <li>hasError() - returns a boolean signifying if an error occurred processing the request</li>
     *     <li>hasResults() - returns a boolean signifying if the response contains data results</li>
     *     <li>getRecordcount() - returns an integer signifying the number of rows affected by the request</li>
     *     <li>getData() - returns a List<Map<String, Object>></li>
     * </ul>
     * </P>
     */
    public final class JdbcResponse {
        private boolean hasResults;
        private int recordcount;
        private List<Map<String, Object>> data = new ArrayList<>();
        private ResearchDatabaseModelException error = null;

        public JdbcResponse() {
            hasResults = false;
        }

        public JdbcResponse(List<Map<String, Object>> results) {
            hasResults = true;
            data.addAll(results);
        }

        public JdbcResponse(int rowsAffected) {
            this.recordcount = rowsAffected;
            hasResults = false;
        }

        public JdbcResponse(Throwable error) {
            error = new ResearchDatabaseModelException(error);
            hasResults = false;
        }

        public boolean hasResults() {
            return hasResults;
        }

        public boolean hasError() {
            return error == null;
        }

        public int getRecordcount() {
            return recordcount;
        }

        public List<Map<String, Object>> getData() {
            List<Map<String, Object>> results = new ArrayList<>();
            if (hasResults) {
                data.stream()
                        .map(results::add).collect(Collectors.toList());
            }
            return results;
        }

        public ResearchDatabaseModelException getError() {
            return error;
        }
    }

}
