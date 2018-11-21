CLS
set CUR_DIR=%cd%
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
set PATH=%JAVA_HOME%\bin\
set CLASSPATH=.;%CUR_DIR%\ThesisSVDLIN\ejml-0.21.jar;%CUR_DIR%\ThesisSVDLIN\Jama-1.0.2.jar

cd %CUR_DIR%\ThesisSVDLIN

echo "SVDPseudo STARTED"
javac *.java
java SVDPseudoInverse > SVDPseudo.txt
echo "SVDPseudo ENDED"