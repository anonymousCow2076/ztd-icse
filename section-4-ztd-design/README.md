# Permissions Manager for Java Dependencies

This project aims to enable the definition of permissions for dependencies in a Java application. This will curtail exploitations of vulnerable dependencies used in an application.

## How to Build
1. Clone the repository
2. In the cloned repository, run `mvn clean package`.
The built agent jars will be located in the target directory.
3. Open the ./target directory in the project root. Verify the `PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar` 
and `PackagePermissionsManager-1.0-SNAPSHOT-shaded.jar` are present.

## How to Use
1. First, modify the `sample-permissions.json` file with the appropriate permissions if necessary
2. Open the `com/anonuser/company/apps` directory and run the `AllPermissionsApp`. Verify the class runs successfully.
3. Run the `AllPermissionsApp` again, this time using the java agent you just built. You can use the following command
`java -javaagent:/path/to/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar -classpath /path/to/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-shaded.jar com.anonuser.company.apps.AllPermissionsApp`
4. Verify the app runs successfully again, this time printing lines that start with `[PERMISSION]`
5. If you wish to run in monitoring mode add `=m[duration]` to the end of the javaagenet jar, this will produce a permissions file every [duration] ms to the standard output

