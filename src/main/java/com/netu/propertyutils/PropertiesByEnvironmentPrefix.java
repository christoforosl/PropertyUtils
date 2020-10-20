package com.netu.propertyutils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Use prefixed properties as per Environment or System property ENVIRONMENT
 * @author christoforosl
 */
public class PropertiesByEnvironmentPrefix extends PropertiesByPrefix {
	
	public PropertiesByEnvironmentPrefix() {
		
		final String propertySys = System.getProperty(ENVIRONMENT);
		final String propertyEnv = System.getenv(ENVIRONMENT);
		
		final String prefix = StringUtils.isNotBlank(propertySys) ? 
										propertySys : 
										propertyEnv;
		
		Validate.notEmpty(prefix, "\"ENVIRONMENT\" system property is empty");
		super.setPropertiesSuffix(prefix);
	
	}
	
	private static final String ENVIRONMENT = "ENVIRONMENT";
}
