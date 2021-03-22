#!/bin/bash

if [ $# -eq 1 ] 
then 
    if [ $1 == "-create" ] 
    then 
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
        jar cmf myapp.mf myapp.jar *.class ../media
    else 
        if [ $1 == "-jar" ] 
        then 
            echo -e "\nLancement de l'archive : "
            java -jar ./bin/myapp.jar
        else 
            echo "Choisir une option possible : "
            echo "'-create' : compilation, création de la Javadoc, du manifeste et du .jar. "
            echo "'-jar' : lancement du .jar. "
        fi
    fi 
else 
    echo "Il faut un paramètre/argument ('-create' ou '-jar'). "
    echo "'-create' : compilation, création de la Javadoc, du manifeste et du .jar. "
    echo "'-jar' : lancement du .jar. "
fi 