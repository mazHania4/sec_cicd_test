@ECHO OFF
REM ----------------------------------------------------------------------------
REM Apache Maven Wrapper startup script (Windows), version 3.2.0
REM ----------------------------------------------------------------------------

SET MAVEN_PROJECTBASEDIR=%~dp0
SET WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.jar

IF NOT EXIST "%WRAPPER_JAR%" (
  echo Descargando Maven Wrapper...
  powershell -Command "(New-Object Net.WebClient).DownloadFile($(Get-Content '%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.properties' | Select-String 'wrapperUrl=' | ForEach-Object { $_.ToString().Split('=')[1] }), '%WRAPPER_JAR%')"
)

IF "%JAVA_HOME%"=="" (
  SET JAVA_CMD=java
) ELSE (
  SET JAVA_CMD=%JAVA_HOME%\bin\java
)

"%JAVA_CMD%" -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
