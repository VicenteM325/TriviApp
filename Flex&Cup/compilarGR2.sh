#!/bin/bash

echo Compilando Lexer...
java -jar jflex-full-1.9.1.jar lexerUs.flex
echo ---------------------

echo Compilando Parser...
java -jar java-cup-11b.jar -parser StorageParser -symbols StorageSym parserUs.cup

mv StorageLexer.java ~/NetBeansProjects/TriviApp/TriviApp/src/main/java/com/triviapp/analizadores/lexico/
mv StorageParser.java StorageSym.java ~/NetBeansProjects/TriviApp/TriviApp/src/main/java/com/triviapp/analizadores/sintactico/

