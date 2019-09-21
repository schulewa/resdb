package com.apschulewitz.resdb.common.model.utils;

import com.apschulewitz.resdb.common.ResearchDatabaseModelException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JdbcUtilsTest {

  private static final String SQL_SCRIPT_DIR = "classpath:scripts/sql";

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private JdbcUtils jdbcUtils;

  @Test
  public void testSpringContext() {
    assertNotNull(applicationContext);
    assertNotNull(jdbcUtils);
  }

  @Test
  public void given_conn_details_when_getConnection_is_executed_then_return_connection() {
    // Given

    // When
    Optional<Connection> connection = jdbcUtils.getConnection();

    // Then
    assertTrue(connection.isPresent());
  }

  @Test
  public void given_connection_when_executeScript_is_executed_with_delete_action_then_return_1row() {
    // Given
    Optional<Connection> optionalConnection = jdbcUtils.getConnection();
    Connection conn = optionalConnection.orElseThrow();
    String insertSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Insert.sql");
    String deleteSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Delete.sql");

    // When
    JdbcUtils.JdbcResponse response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Insert, insertSqlFile);
    response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Delete, deleteSqlFile);

    // then
    assertNotNull(response);
    assertEquals(1, response.getRecordcount());
  }

  @Test
  public void given_connection_when_executeScript_is_executed_with_insert_action_then_return_1rows() {
    // Given
    Optional<Connection> optionalConnection = jdbcUtils.getConnection();
    Connection conn = optionalConnection.orElseThrow();
    String deleteSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Delete.sql");
    String insertSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Insert.sql");

    // When
    JdbcUtils.JdbcResponse response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Delete, deleteSqlFile);
    response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Insert, insertSqlFile);

    // then
    assertNotNull(response);
    assertFalse(response.hasError());
    assertFalse(response.hasResults());
    assertEquals(1, response.getRecordcount());
  }

  @Test
  public void given_connection_when_executeScript_is_executed_with_update_action_then_return_1rows() {
    // Given
    Optional<Connection> optionalConnection = jdbcUtils.getConnection();
    Connection conn = optionalConnection.orElseThrow();
    String deleteSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Delete.sql");
    String insertSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Insert.sql");
    String updateSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Update.sql");

    // When
    JdbcUtils.JdbcResponse response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Delete, deleteSqlFile);
    response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Insert, insertSqlFile);
    response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Update, updateSqlFile);

    // then
    assertNotNull(response);
    assertFalse(response.hasError());
    assertFalse(response.hasResults());
    assertEquals(1, response.getRecordcount());
  }

  @Test
  public void given_connection_when_executeScript_is_executed_with_select_action_then_return_1row() {
    // Given
    Optional<Connection> optionalConnection = jdbcUtils.getConnection();
    Connection conn = optionalConnection.orElseThrow();
    String deleteSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Delete.sql");
    String insertSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Insert.sql");
    JdbcUtils.JdbcResponse response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Delete, deleteSqlFile);
    response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Insert, insertSqlFile); // populate our table

    // When
    String selectSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Select.sql");
    response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Select, selectSqlFile);

    // then
    assertNotNull(response);
    assertFalse(response.hasError());
    assertTrue(response.hasResults());
    assertEquals(1, response.getRecordcount());
  }

  @Test(expected = ResearchDatabaseModelException.class)
  public void given_connection_when_createStatement_is_executed_and_exception_is_thrown_then_return_error() throws SQLException {
    // Given
    Connection mockedConnection = mock(Connection.class);
    String deleteSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "JDbcUtilsTest-Delete.sql");

    // When
    when(mockedConnection.createStatement()).thenThrow(SQLException.class);
    JdbcUtils.JdbcResponse response = jdbcUtils.executeScript(mockedConnection, JdbcUtils.JdbcActionType.Delete, deleteSqlFile);

    // then

  }

  @Test(expected = ResearchDatabaseModelException.class)
  public void given_invalid_script_name_when_executeScript_is_executed_with_delete_action_then_return_error() {
    // Given
    Optional<Connection> optionalConnection = jdbcUtils.getConnection();
    Connection conn = optionalConnection.orElseThrow();
    String deleteSqlFile = String.format("%s%s%s",SQL_SCRIPT_DIR, File.separator, "bad-file-name.sql");

    // When
    JdbcUtils.JdbcResponse response = jdbcUtils.executeScript(conn, JdbcUtils.JdbcActionType.Delete, deleteSqlFile);

    // Then

  }

  @Test
  public void given_action_type_and_scriptname_when_constructor_is_executed_then_return_jdbcrequest() {
    // Given
    JdbcUtils.JdbcActionType type = JdbcUtils.JdbcActionType.Delete;
    String scriptName = "JDbcUtilsTest-Delete.sql";

    // When
    JdbcUtils.JdbcRequest request = jdbcUtils.new JdbcRequest(type, scriptName);

    // Then
    assertNotNull(request);
    assertEquals(type, request.getType());
    assertEquals(scriptName, request.getScriptName());
  }

  @Test
  public void given_no_args_when_constructor_is_executed_then_return_jdbcresponse() {
    // Given


    // When
    JdbcUtils.JdbcResponse response = jdbcUtils.new JdbcResponse();

    // Then
    assertNotNull(response);
    assertFalse(response.hasResults());
  }
}
