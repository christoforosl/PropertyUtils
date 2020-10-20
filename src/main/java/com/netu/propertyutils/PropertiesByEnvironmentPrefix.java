package com.netu.propertyutils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Use prefixed properties as per Environment or System property ENVIRONMENT
 * @author christoforosl
 */
public class PropertiesByEnvironmentPrefix extends PropertiesByPrefix {
	
	public PropertiesByEnvironmentPrefix() {
		
		final String prefix = StringUtils.isBlank(System.getProperty(ENVIRONMENT)) ? 
										System.getProperty(ENVIRONMENT) : 
										System.getenv(ENVIRONMENT);
		
		Validate.notEmpty(prefix, "\"ENVIRONMENT\" system property is empty");
		super.setPropertiesPrefix(prefix);
	
	}
	private static final String ENVIRONMENT = "ENVIRONMENT";
}
