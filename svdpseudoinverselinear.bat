CLS
set CUR_DIR=%cd%
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
set PATH=%JAVA_HOME%\bin\;%PATH%
set CLASSPATH=.;%CUR_DIR%\ThesisSVDLIN\ejml-0.21.jar;%CUR_DIR%\ThesisSVDLIN\Jama-1.0.2.jar;%CLASSPATH

cd %CUR_DIR%\ThesisSVDLIN

echo "SVDPseudoLin STARTED"
javac *.java
java SVDPseudoInverseWithLinearSolver > SVDPseudoLin.txt
echo "SVDPseudoLin ENDED"