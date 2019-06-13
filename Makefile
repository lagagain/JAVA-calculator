PROJECTS     = test JAVA-GUI
PACKAGE_DIR  = ./package/
LUAJ_JSE_FILE_NAME = luaj-jse-3.0.1.jar
LUAJ_JSE_LIB = ${PACKAGE_DIR}luaj-3.0.1/lib/${LUAJ_JSE_FILE_NAME}
BIN_DIR      = ./bin/
SRC_DIR      = ./src/

JAVA-GUI: ${SRC_DIR}JAVA-GUI/GUI.java ${SRC_DIR}JAVA-GUI/cal.lua
	mkdir -p ${BIN_DIR}JAVA-GUI
	javac -classpath ${LUAJ_JSE_LIB} ${SRC_DIR}JAVA-GUI/GUI.java -d ${BIN_DIR}JAVA-GUI
	javac -classpath ${LUAJ_JSE_LIB} ${SRC_DIR}JAVA-GUI/CMessageBox.java -d ${BIN_DIR}JAVA-GUI
	cp ${SRC_DIR}JAVA-GUI/cal.lua ${BIN_DIR}JAVA-GUI
	cp ${SRC_DIR}JAVA-GUI/extend-1.txt ${BIN_DIR}JAVA-GUI
	java -cp ${LUAJ_JSE_LIB} luac -o ${BIN_DIR}JAVA-GUI/cal ${BIN_DIR}JAVA-GUI/cal.lua;\
	#rm ${BIN_DIR}JAVA-GUI/cal.lua
	cp ${LUAJ_JSE_LIB} ${BIN_DIR}/JAVA-GUI
	echo "Main-Class: GUI" >  ${BIN_DIR}JAVA-GUI/MANIFEST.MF
	#echo "Class-Path: ${LUAJ_JSE_FILE_NAME}" >> ${BIN_DIR}JAVA-GUI/MANIFEST.MF
	cd ${BIN_DIR}JAVA-GUI;\
	uz ${LUAJ_JSE_FILE_NAME};\
	jar cvfm cal.jar MANIFEST.MF ./;\
	echo '#! /bin/bash' > run.sh;\
	echo 'java -jar cal.jar' >> run.sh;\
	chmod a+x run.sh;\
	echo "java -jar cal.jar" > run.bat;\
	rm *.class MANIFEST.MF ${LUAJ_JSE_FILE_NAME};\
	rm -r org META-INF
