//============================================================================
// Name        : Configuration.java
// Created on  : October 31, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Configuration class
//============================================================================

package smtp.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** @class DefaultValues : */
final class DefaultValues {
	/** SMTP server host default value. **/	
	public static final String smtpDefaultHost = "smtp.yandex.ru";
	
	/** SMTP server port default value. **/	
	public static final int smtpDefaultPort = 465;
	
	/** Mail DEBUG switch flag default value. **/	
	public static final boolean smtpDefaultDebug = false;
	
	/** SMTP authorization default value. **/	
	public static final boolean smtpDefaultAuth = false;
	
	/** SMTP java socket factory port default value. **/	
	public static final int smtpDefaultSocketFactoryPort = 465;
	
	/** SMTP java socket factory class default value. **/	
	public static final String smtpDefaultSocketFactoryClass = "mail.smtp.socketFactory.class";
	
	/** SMTP java socket factory fallback default value. **/	
	public static final boolean smtpDefaultSocketFactoryFallback = false;
}

/** @class PropertiesNames : */
final class PropertiesNames {
	/** SMTP server host parameter name. **/
	public static final String smtpHostParameterName = "mail.smtp.host";
	
	/** SMTP server port parameter name. **/
	public static final String smtpPortParameterName = "mail.smtp.port";
	
	/** Mail DEBUG swith flag parameter name. **/
	public static final String mailDebugParameterName = "mail.debug";
	
	/** SMTP authorization parameter name. **/
	public static final String smtpAuthParameterName = "mail.smtp.auth";
	
	/** SMTP java socket factory port parameter name. **/
	public static final String smtpSocketFactoryPortParameterName = "mail.smtp.socketFactory.port";
	
	/** SMTP java socket factory class parameter name. **/
	public static final String smtpSocketFactoryClassParameterName = "mail.smtp.socketFactory.class";
	
	/** SMTP java socket factory fallback parameter name. **/
	public static final String smtpSocketFactoryFallbackParameterName = "mail.smtp.socketFactory.fallback";	
}

/** @class Configuration : */
public class Configuration
{
	/** Configuration static instance. **/
	private static Configuration __instance = null;
	/** Default configuration file name :**/
	private static String DEFAULT_PROPERTIES_FILE = "src/config.properties";
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
	
	/** JUST FOR TESTING PURPOSES !!!!!!!!!!! **/
	public static void setPropertiesForTesting(final String propsFile) {
		Configuration.DEFAULT_PROPERTIES_FILE = propsFile;
	}

	/**  **/  
	public static Configuration getConfiguration() {
		if (null == __instance) {
			synchronized (Configuration.class) {
				if (null == __instance) {
					__instance = new Configuration(Configuration.DEFAULT_PROPERTIES_FILE);
				}
			}
		} return __instance;
	}

	public String getSmtpHost() {
		final String value = property.getProperty(PropertiesNames.smtpHostParameterName);
		return null == value ? DefaultValues.smtpDefaultHost : value;
	}

	public int getSmtpPort() {
		final String value = property.getProperty(PropertiesNames.smtpPortParameterName);
		return null == value ? DefaultValues.smtpDefaultPort : Integer.valueOf(value);
	}

	public boolean isDebug() {
		final String value = property.getProperty(PropertiesNames.mailDebugParameterName);
		return null == value ? DefaultValues.smtpDefaultDebug : Boolean.valueOf(value);
	}
	
	public boolean isAuthorizationRequired() { 
		final String value = property.getProperty(PropertiesNames.smtpAuthParameterName);
		return null == value ? DefaultValues.smtpDefaultAuth : Boolean.valueOf(value);
	}

	public int getSocketFactoryPort () {
		final String value = property.getProperty(PropertiesNames.smtpSocketFactoryPortParameterName);
		return null == value ? DefaultValues.smtpDefaultSocketFactoryPort : Integer.valueOf(value);
	}	
	
	public String getSocketFactoryClass () {
		final String value = property.getProperty(PropertiesNames.smtpSocketFactoryClassParameterName);
		return null == value ? DefaultValues.smtpDefaultSocketFactoryClass : value;
	}	
	
	public boolean getSocketFactoryFallback () {
		final String value = property.getProperty(PropertiesNames.smtpSocketFactoryFallbackParameterName);
		return null == value ? DefaultValues.smtpDefaultSocketFactoryFallback : Boolean.valueOf(value);
	}	


 	public static void main(String[] args) throws IOException
	{
 		Configuration config = Configuration.getConfiguration();
 		System.out.println(PropertiesNames.smtpHostParameterName + " = " + config.getSmtpHost());
 		System.out.println(PropertiesNames.mailDebugParameterName + " = " + config.isDebug());
 		System.out.println(PropertiesNames.smtpAuthParameterName + " = " + config.isAuthorizationRequired());
 		System.out.println(PropertiesNames.smtpPortParameterName + " = " + config.getSmtpPort());
 		System.out.println(PropertiesNames.smtpSocketFactoryPortParameterName + " = " + config.getSocketFactoryPort());
 		System.out.println(PropertiesNames.smtpSocketFactoryClassParameterName + " = " + config.getSocketFactoryClass());
 		System.out.println(PropertiesNames.smtpSocketFactoryFallbackParameterName + " = " + config.getSocketFactoryFallback());
 	}
}