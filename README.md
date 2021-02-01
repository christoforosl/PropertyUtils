# PropertyUtils
Java Property utilities

Suffix and store properties for multiple environments in a single properties file, read them according to the current environment you are running.

Example:

```
jdbcurl.driver-DEV=com.mysql.cj.jdbc.Driver
jdbcurl.driver-PROD=com.mysql.cj.jdbc.Driver
jdbcurl.driver-PREPROD=com.mysql.cj.jdbc.Driver
```

In order to read DEV, PROD, PREPROD properties, you can define an environment or command line option like this:
`-DENVIROMENT=PROD`
