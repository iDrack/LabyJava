#!/bin/bash
echo -e "\nCompilations des classes Java .."
cd src
javac -d ../bin *.java

echo -e "\nCréation de la Javadoc : "
javadoc -encoding UTF-8 -d ../doc *.java

echo -e "\nCréation de l'archive autoexécutable Java : "
cd ../bin
touch myapp.mf

echo "Manifest-Version: 1.0" > myapp.mf
echo "Class-Path: bin/" >> myapp.mf
echo "Main-Class: MainWindow" >> myapp.mf

echo -e "\nCréation du .jar Java : "
jar cmf myapp.mf myapp.jar *.class

echo -e "\nLancement de l'archive : "
java -jar myapp.jar