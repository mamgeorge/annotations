Spring Annotations

http://zetcode.com/springboot/annotations

	> cd c:\workspace/github/spring_annotations

	> mvn clean install
	> mvn compile
	> mvn test
	> mvn spring-boot:run

	http://localhost:8080/

Another way to run this is below, but the classPath changes for internal resources:

	> mvn exec:java -Dexec.mainClass="com.basics.BasicsApplication"

Similarly, you can run the Utility class with this

	// note: needs forward slash if from screen
	set JAVA_TEMPCP=C:\workspace\github\spring_annotations\target\classes;C:\Users\mamge\.m2\repository\org\json\org.json\chargebee-1.0\org.json-chargebee-1.0.jar;C:\Users\mamge\.m2\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-yaml\2.11.2\jackson-dataformat-yaml-2.11.2.jar;C:\Users\mamge\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.11.1\jackson-core-2.11.1.jar;C:\Users\mamge\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.11.1\jackson-databind-2.11.1.jar

	java -cp %JAVA_TEMPCP% com.basics.util.UtilityExtra
	java -cp $env:JAVA_TEMPCP com.basics.util.UtilityExtra // for PowerShell

search filters

	*.java;*.js;*.json;*.xml;*.txt;*.yml;*.properties,*.flth

