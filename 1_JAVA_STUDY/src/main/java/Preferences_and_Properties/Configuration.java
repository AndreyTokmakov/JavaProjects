//============================================================================
// Name        : Configuration.java
// Created on  : October 31, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Configuration class
//============================================================================

package Preferences_and_Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/** @class DefaultValues : */
class DefaultValues {
	/** Database server host IP address. **/
	public static final String dbHost = "100.99.4.54";
	
	/** Database server connection port. **/	
	public static final int dbPort = 3306;
	
	/** Database scheme name. **/	
	public static final String dbScheme = "autotesting";	
	
	/** Database authorization user name. **/
	public static final String dbUserLogin = "tester";
	
	/** Database authorization password. **/
	public static final String dbUserPassword = "tester";
	
	/** Database authorization password. **/
	public static final String kafkaNodesListDefault = "[ode1, node2]";
}


/** @class PropertiesNames : */
class PropertiesNames {
	/** Database server host IP parameter name: **/
	public static final String dbHostParamName = "db.host";
	
	/** Database server connection port parameter name: **/
	public static final String dbPortParamName = "db.port";
	
	/** Database scheme parameter name: **/
	public static final String dbSchemeParamName = "db.scheme";
	
	/** Database authorization user name: **/
	public static final String dbLoginParamName = "db.login";
	
	/** Database authorization password parameter name: **/
	public static final String dbPasswordParamName = "db.password";	

	/** Database authorization password parameter name: **/
	public static final String kafkaNodesList = "kafka.nodes";	
}


/** @class Configuration : */
public class Configuration {
	/** Configuration static instance. **/
	private static Configuration __instance = null;
	/** Default configuration file name :**/
	private final static String DEFAULT_PROPERTIES_FILE = "src/properties/config.properties";
	/** Properties: **/
	protected Properties property = new Properties();

	/** Configuration class constructor: **/
	protected Configuration(final String propFile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(propFile);
			property.load(fileInputStream);
		}  catch (final IOException exc) {
			System.err.println("Failed to read file " + propFile);
		}
	}

	/**  **/  
	public static Configuration getConfiguration() {
		if (null == __instance) {
			synchronized (Configuration.class) {
				if (null == __instance) {
					__instance = new Configuration(Configuration.DEFAULT_PROPERTIES_FILE);
				}
			}
		}
		return __instance;
	}

	public String getDbHost() {
		final String value = property.getProperty(PropertiesNames.dbHostParamName);
		return null == value ? DefaultValues.dbHost : value;
	}

	public int getDbPort() {
		final String value = property.getProperty(PropertiesNames.dbPortParamName);
		return null == value ? DefaultValues.dbPort : Integer.valueOf(value);
	}

	public String getDbSort() {
		final String value = property.getProperty(PropertiesNames.dbSchemeParamName);
		return null == value ? DefaultValues.dbScheme : value;
	}
	
	public String getDbLogin() {
		final String value = property.getProperty(PropertiesNames.dbLoginParamName);
		return null == value ? DefaultValues.dbUserLogin : value;
	}

	public String getDbPassword() {
		final String value = property.getProperty(PropertiesNames.dbPasswordParamName);
		return null == value ? DefaultValues.dbUserPassword : value;
	}

	public String getKafkaNodes() {
		final String value = property.getProperty(PropertiesNames.kafkaNodesList);
		return null == value ? DefaultValues.kafkaNodesListDefault : value;
	}	
	
	////////////////////////////////////

	public static void main(String args[]) {
		Configuration config = Configuration.getConfiguration();
		System.out.println(PropertiesNames.dbHostParamName + " = " + config.getDbHost());
		System.out.println(PropertiesNames.dbPortParamName + " = " + config.getDbPort());
		System.out.println(PropertiesNames.dbSchemeParamName + " = " + config.getDbSort());
		System.out.println(PropertiesNames.dbLoginParamName + " = " + config.getDbLogin());
		System.out.println(PropertiesNames.dbPasswordParamName + " = " + config.getDbPassword());
		
		String nodesStr = config.getKafkaNodes();
		nodesStr = nodesStr.substring(1, nodesStr.length() - 1);
		List<String> nodes = new ArrayList<String>(Arrays.asList(nodesStr.split(",")));
		
		
		for (final String node: nodes)
			System.out.println(node);
		
		
	}
}