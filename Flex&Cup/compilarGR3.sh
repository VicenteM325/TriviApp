#!/bin/bash

echo Compilando Lexer...
java -jar jflex-full-1.9.1.jar lexerRes.flex
echo ---------------------

echo Compilando Parser...
java -jar java-cup-11b.jar -parser ResponseParser -symbols ResponseSym parserRes.cup

mv ResponseLexer.java ~/NetBeansProjects/TriviApp/TriviApp/src/main/java/com/response/analizadores/lexico/
mv ResponseParser.java ResponseSym.java ~/NetBeansProjects/TriviApp/TriviApp/src/main/java/com/response/analizadores/sintactico/

