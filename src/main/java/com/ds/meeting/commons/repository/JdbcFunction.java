package com.ds.meeting.commons.repository;

import java.util.Map;

public interface JdbcFunction {
    Object getFunctionResult(String functionName, Map<String, Object> param);
    Map<String, Object> getProcedureResult(String procedureName, Map<String, Object> inParam);
}
