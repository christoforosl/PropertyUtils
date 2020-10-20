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
 */
public class PropertiesByEnvironmentPrefix {

	private String propertiesPrefix;
	private String propertiesResourceName;
	private Properties properties;

	public String getPropertiesPrefix() {
		return propertiesPrefix;
	}

	public PropertiesByEnvironmentPrefix setPropertiesPrefix(String propertiesPrefix) {
		this.propertiesPrefix = propertiesPrefix;
		return this;
	}

	
	public String getProperty(final String pkey, final String defaultVal) {

		String ret = this.getProperty(pkey);
		if (StringUtils.isBlank(ret)) {
			ret = defaultVal;
		}
		return ret;

	}

	public String getProperty(final String pkey) {
		final String propertyValue = this.getProperties().getProperty(propertiesPrefix+pkey);
		return propertyValue == null ? null : propertyValue.trim();
	}

	public String getMandatoryProperty(final String pkey) {
		final String ret = this.getProperty(pkey);
		Validate.notEmpty(ret, "Property \"" + pkey + "\" is missing and it is required!");
		return ret;
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

	public PropertiesByEnvironmentPrefix setPropertiesResourceName(final String propertiesResourceName) {
		this.propertiesResourceName = propertiesResourceName;
		return this;
	}

}
