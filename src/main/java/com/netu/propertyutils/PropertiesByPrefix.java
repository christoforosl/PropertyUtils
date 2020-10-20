package com.netu.propertyutils;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Java Properties manager, by environment prefix.
 * The properties are retrieved by prefixing them with the "propertiesPrefix" string 
 * 
 * The idea is to have a single file containing the same property with different prefix, for 
 * each environment 
 * 
 * @author christoforosl
 * 
 */
public class PropertiesByPrefix {

	private String propertiesPrefix;
	private String propertiesResourceName;
	private Properties properties;

	public String getPropertiesPrefix() {
		return propertiesPrefix;
	}

	public PropertiesByPrefix setPropertiesPrefix(String propertiesPrefix) {
		this.propertiesPrefix = propertiesPrefix;
		return this;
	}

	
	public String getProperty(final String pkey, final String defaultVal) {

		return getProperty( pkey, false, defaultVal );

	}
	
	public String getMandatoryProperty(final String pkey) {
		final String ret = this.getProperty(pkey, true, null);
		return ret;
	}
	
	public String getProperty(final String pkey , final boolean mandatory, final String defaultVal) {
		
		final String propname = propertiesPrefix+pkey;
		String propertyValue = this.getProperties().getProperty(propname);
		
		if(mandatory) {
			Validate.isTrue ( StringUtils.isNotBlank(propertyValue) , 
				"Property \"" + pkey + "\" is missing and it is required!");
		} else {
			if (StringUtils.isBlank(propertyValue) && StringUtils.isNotBlank(defaultVal)) {
				propertyValue = defaultVal;
			}
		}
		return propertyValue == null ? null : propertyValue.trim();
		
	}

	

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		if (properties == null) {
			properties = new Properties();

			final String propsFile = getPropertiesResourceName();
			System.out.println("Using Properties file: " + propsFile);

			try (final InputStream asStream = this.getClass().getResourceAsStream(propsFile)) {
				Validate.notNull(asStream, "Cant open properties file:" + propsFile);
				properties.load(asStream);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return properties;
	}

	public String getPropertiesResourceName() {

		return propertiesResourceName;
	}

	public PropertiesByPrefix setPropertiesResourceName(final String propertiesResourceName) {
		this.propertiesResourceName = propertiesResourceName;
		return this;
	}

}
