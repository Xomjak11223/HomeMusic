@echo off
REM ==============================================
REM  Local HTTPS Setup for Spring Boot (Windows)
REM ==============================================

setlocal enabledelayedexpansion

REM ==============================================
REM  Load variables from .env file (one directory up)
REM ==============================================
if not exist "..\.env" (
    echo [ERROR] .env file not found in parent directory.
    pause
    exit /b 1
)

for /f "usebackq tokens=1,2 delims==" %%A in ("..\.env") do (
    if not "%%A"=="" (
        set %%A=%%B
    )
)

REM Default values (can be overridden by .env)
if "%YOUR_IP%"=="" set YOUR_IP=127.0.0.1
if "%KEYSTORE_PASSWORD%"=="" set KEYSTORE_PASSWORD=secret
if "%KEYSTORE_FILE%"=="" set KEYSTORE_FILE=keystore.p12
if "%ALIAS%"=="" set ALIAS=localserver
if "%VALIDITY_DAYS%"=="" set VALIDITY_DAYS=3650

REM ==============================================
REM  Target directory for the keystore
REM ==============================================
set TARGET_PATH=..\src\main\resources\%KEYSTORE_FILE%

echo.
echo ================================
echo   Starting Local HTTPS Setup
echo ================================

REM Check if keytool is available
where keytool >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] keytool not found. Please install OpenJDK or JRE.
    pause
    exit /b 1
)

REM Remove old certificate if it exists
if exist "%TARGET_PATH%" (
    echo Found existing certificate - deleting...
    del "%TARGET_PATH%"
)

echo Creating certificate for %YOUR_IP% ...
keytool -genkeypair ^
 -alias %ALIAS% ^
 -keyalg RSA ^
 -keysize 2048 ^
 -keystore "%TARGET_PATH%" ^
 -storetype PKCS12 ^
 -validity %VALIDITY_DAYS% ^
 -storepass %KEYSTORE_PASSWORD% ^
 -dname "CN=%YOUR_IP%, OU=Local, O=Local, L=LAN, S=LAN, C=DE"

echo.
echo ==============================================
echo Certificate created (wait ~5s) :
echo    %TARGET_PATH%
echo.
echo Start your Spring Boot project with:
echo    mvnw spring-boot:run
echo.
echo Open in your browser:
echo    https://%YOUR_IP%:8443
echo (Accept the browser warning on first use)
echo ==============================================

pause
