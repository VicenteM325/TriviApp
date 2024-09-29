#!/bin/bash

echo Compilando Lexer...
java -jar jflex-full-1.9.1.jar lexer.flex
echo ---------------------

echo Compilando Parser...
java -jar java-cup-11b.jar -parser parser -symbols RequestsSym parser.cup

mv RequestsLexer.java ~/NetBeansProjects/TriviApp/TriviApp/src/main/java/com/triviapp/analizadores/lexico/
mv parser.java RequestsSym.java ~/NetBeansProjects/TriviApp/TriviApp/src/main/java/com/triviapp/analizadores/sintactico/

