/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 *
 * @author sawthiha
 */
public class DBConnection implements Connection{
    public DBConnection(Connection liaison)  {
        this.liaison = liaison;
    }
    
    @Override
    public Statement createStatement() throws SQLException {
        return this.liaison.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.liaison.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return this.liaison.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return this.liaison.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.liaison.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return this.liaison.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        this.liaison.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.liaison.rollback();
    }

    @Override
    public void close() throws SQLException {
        this.liaison.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.liaison.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return this.liaison.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        this.liaison.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return this.liaison.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        this.liaison.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return this.liaison.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        this.liaison.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return this.liaison.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.liaison.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        this.liaison.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.liaison.createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.liaison.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.liaison.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.liaison.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        this.liaison.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        this.liaison.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return this.liaison.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return this.liaison.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return this.liaison.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        this.liaison.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.liaison.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.liaison.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.liaison.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.liaison.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return this.liaison.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return this.liaison.prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return this.liaison.prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return this.liaison.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return this.liaison.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return this.liaison.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return this.liaison.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return this.liaison.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        this.liaison.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.liaison.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return this.liaison.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return this.liaison.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return this.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return this.liaison.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        this.liaison.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return this.liaison.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        this.liaison.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        this.liaison.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return this.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.liaison.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.liaison.isWrapperFor(iface);
    }
    
    /**
     * Delegate Connection
     */
    private final Connection liaison;
}
