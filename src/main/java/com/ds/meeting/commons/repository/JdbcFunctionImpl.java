package com.ds.meeting.commons.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcFunctionImpl implements JdbcFunction {

    private final JdbcTemplate jdbcTemplate;

    @Value("${DB_NAME}")
    private String dbName;


    @Override
    public Object getFunctionResult(String functionName, Map<String, Object> param) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName(functionName).withCatalogName(dbName);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(param);
        return jdbcCall.executeFunction(Object.class, mapSqlParameterSource);
    }


    @Override
    public Map<String, Object> getProcedureResult(String procedureName, Map<String, Object> inParam) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procedureName).withCatalogName(dbName);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(inParam);
        return jdbcCall.execute(mapSqlParameterSource);
    }

}
