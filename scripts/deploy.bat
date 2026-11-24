@echo off

echo ===============================
echo VERIFICANDO WILDFLY
echo ===============================

cd C:\Dev\wildfly\bin

timeout /t 2 > nul

netstat -ano | findstr :9990 > nul

if %errorlevel%==0 (
    echo WildFly encontrado. Desligando...
    jboss-cli.bat --connect command=:shutdown
) else (
    echo WildFly ja esta desligado.
)


echo ===============================
echo MAVEN CLEAN
echo ===============================

cd %~dp0

call mvn clean


echo ===============================
echo REMOVENDO WAR ANTIGO
echo ===============================

del C:\Dev\wildfly\standalone\deployments\projetoPrimeFaces-1.0-SNAPSHOT.war


echo ===============================
echo GERANDO WAR
echo ===============================

call mvn package
echo TESTANDO WAR

dir target

echo TESTANDO DEPLOYMENTS

dir C:\Dev\wildfly\standalone\deployments

pause

echo ===============================
echo COPIANDO WAR PARA WILDFLY
echo ===============================

copy target\projetoPrimeFaces-1.0-SNAPSHOT.war C:\Dev\wildfly\standalone\deployments


echo ===============================
echo INICIANDO WILDFLY
echo ===============================

cd C:\Dev\wildfly\bin

call standalone.bat