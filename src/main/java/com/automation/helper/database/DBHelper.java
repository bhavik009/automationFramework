package com.automation.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.automation.helper.browser.config.ObjectReader;
import com.automation.helper.browser.config.PropertyReader;
import com.automation.helper.logger.LoggerHelper;

public class DBHelper {

	private static Logger log = LoggerHelper.getLogger(DBHelper.class);

	private static String dbURL;
	private static String driverName;
	private static String dbUsername;
	private static String dbPassword;
	private static Connection connection;
	private static DBHelper instance = null;

	public DBHelper() {

		ObjectReader.reader = new PropertyReader();

		dbURL = "jdbc:mysql://" + ObjectReader.reader.getDBUrl();
		driverName = "com.mysql.jdbc.Driver";
		dbUsername = ObjectReader.reader.getDBUsername();
		dbPassword = ObjectReader.reader.getDBPassword();

		connection = getSingleInstanceConnection();
	}

	public static DBHelper getInstance() {
		if (instance == null) {
			instance = new DBHelper();
		}
		return instance;
	}

	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
				if (connection != null) {
					log.info("Connected to data base..");
				}
			} catch (SQLException e) {
				log.error("Failed to create Data base connection.." + e);
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not found.." + e);
		}
		return connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public static ResultSet getResultSet(String dbQuery) {
		instance = DBHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing query: " + dbQuery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
