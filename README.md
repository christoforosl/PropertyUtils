# PropertyUtils
Java Property utilities

Suffix and store properties for multiple environments in a single properties file, read them according to the current environment you are running.

Example:

```
\# example property app.url
app.url-DEV=http://localhost:9002/
app.url-PROD=http://prod.herokuapp.com/
app.url-PREPROD=http://preprod.herokuapp.com/

\# example property jdbcurl.driver
jdbcurl.driver-DEV=com.mysql.cj.jdbc.Driver
jdbcurl.driver-PROD=com.mysql.cj.jdbc.Driver
jdbcurl.driver-PREPROD=com.mysql.cj.jdbc.Driver
```

In production, you want to ready the PROD suffixed properties from the file above, so you should define an environmental variable like this:
```java ... -DENVIROMENT=PROD```
