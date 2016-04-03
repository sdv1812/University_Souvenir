CD %~dp0

CALL setenv.bat

SET CUR_DIR=%CD%

if not exist classes mkdir classes

CD ./src/sg/edu/nus/iss

javac -d %CUR_DIR%/classes dao/*.java store/*.java exceptions/*.java gui/*.java utils/*.java