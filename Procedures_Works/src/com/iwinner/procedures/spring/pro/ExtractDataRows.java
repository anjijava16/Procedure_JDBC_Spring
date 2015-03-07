package com.iwinner.procedures.spring.pro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


/**
 * The Class ExtractDataRows.
 */
@SuppressWarnings("unchecked")
public class ExtractDataRows extends StoredProcedure {

	/** The Constant LOGGER. */
//	private static final Logger LOGGER = Logger.getLogger(ExtractDataRows.class);
	
	/** The stored procedure name. */
	private String storedProcedureName = null;

	/**
	 * Instantiates a new extract data rows.
	 *
	 * @param dataSource the data source
	 * @param storedProcedureName the stored procedure name
	 * @param sqlParams the sql params
	 * @throws Exception the dAO exception
	 */
	public ExtractDataRows(DataSource dataSource, String storedProcedureName, SqlParameter[] sqlParams) throws Exception {
		super(dataSource, storedProcedureName);
		super.setParameters(sqlParams);
		this.storedProcedureName = storedProcedureName;
		compile();

	}

	/**
	 * Instantiates a new extract data rows.
	 *
	 * @param dataSource the data source
	 */
	public ExtractDataRows(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Execute stored procedure.
	 *
	 * @param inputs the inputs map
	 * @return the map
	 * @throws Exception the dAO exception
	 */
	public Map executeStoredProcedure(HashMap inputs) throws Exception {
		try {
		//	LOGGER.debug("Calling execute() of ExtractDataRows Inner class");
			constructSPCallSyntax(storedProcedureName, inputs);
			return execute(inputs);
		} catch (DataAccessException dataAccessException) {
		//	LOGGER.error(dataAccessException.getMessage(), dataAccessException);
			throw new Exception(dataAccessException);
		}
	}

	/**
	 * Construct Stored Procedure call syntax.
	 *
	 * @param storedProcedureName the stored procedure name
	 * @param inputParams the input params
	 */
	private void constructSPCallSyntax(String storedProcedureName, HashMap inputParams) {
		Iterator it = inputParams.keySet().iterator();
		StringBuffer query = new StringBuffer();
		query.append(storedProcedureName);
		query.append(" call(");

		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = inputParams.get(key);
			query.append(key + "=" + value + ", ");
		}
		query.append(")");
		//System.out.println(query.toString());
		//  LOGGER.debug(query.toString());
	}
	

}
