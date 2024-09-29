package com.triviapp.analizadores.lexico;


import java_cup.runtime.Symbol;
import static com.triviapp.analizadores.sintactico.StorageSym.*;

%%

%class StorageLexer
%public
%cup
%unicode
%line
%column

%{

    private Symbol symbol(int type){
        return new Symbol(type, yytext());
    }

%}

%eofval{
    return new Symbol(EOF, "Fin de linea");
%eofval}

ENTERO = "\""(0|([1-9][0-9]*))"\""

ID = "\""[\_\-\$](\w|[\_\-\$])*"\""
FECHA = "\""\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])"\""
OPCIONES = "\""((\w+\|\w+)(\|\w+)*)"\""
OPCIONES2 = "\""((\w+\,\w+)(\,\w+)*)"\""
VALUE = "\""[^ '\"']*"\""
LITERAL = "\""[^"\""]*"\""
    
%%


<YYINITIAL> "db.user"                       {return symbol(INICIO_USER);}
<YYINITIAL> "db.trivia"                       {return symbol(INICIO_FORM);}
<YYINITIAL> "db.recopiledData"              {return symbol(INICIO_DATOS);}

<YYINITIAL> "\"USUARIO\""                   {return symbol(PARAM_USUARIO);}
<YYINITIAL> "\"PASSWORD\""                  {return symbol(PARAM_PASSWORD);}
<YYINITIAL> "\"FECHA_CREACION\""            {return symbol(PARAM_FECHA_CREACION);}
<YYINITIAL> "\"FECHA_MODIFICACION\""        {return symbol(PARAM_FECHA_MODIFICACION);}
<YYINITIAL> "\"NOMBRE\""                    {return symbol(PARAM_NOMBRE);}
<YYINITIAL> "\"INSTITUCION\""                {return symbol(PARAM_INSTITUCION);}

<YYINITIAL> "\"ID_TRIVIA\""                        {return symbol(PARAM_ID);}
<YYINITIAL> "\"TIEMPO_PREGUNTA\""                 {return symbol(PARAM_TIEMPO_TRIV);}
<YYINITIAL> "\"NOMBRE\""                    {return symbol(PARAM_NOMBRE_FORM);}
<YYINITIAL> "\"TEMA\""                      {return symbol(PARAM_TEMA);}
<YYINITIAL> "\"USUARIO_CREACION\""          {return symbol(PARAM_USUARIO_CREACION);}

<YYINITIAL> "\"COMPONENTES\""               {return symbol(PARAM_COMPONENTES);}

//<YYINITIAL> "\"ID\""              {return symbol(PARAM_NOMBRE_CAMPO);}
<YYINITIAL> "\"TRIVIA\""                {return symbol(PARAM_FORMULARIO);}
<YYINITIAL> "\"CLASE\""                     {return symbol(PARAM_CLASE);}
<YYINITIAL> "\"TEXTO_VISIBLE\""             {return symbol(PARAM_TEXTO_VISIBLE);}
<YYINITIAL> "\"OPCIONES\""                  {return symbol(PARAM_OPCIONES);}
<YYINITIAL> "\"FILAS\""                     {return symbol(PARAM_FILAS);}
<YYINITIAL> "\"COLUMNAS\""                  {return symbol(PARAM_COLUMNAS);}
<YYINITIAL> "\"RESPUESTAS\""                  {return symbol(PARAM_RESPUESTAS);}

<YYINITIAL> "\"CAMPO_TEXTO\""               {return symbol(CLASS_CAMPO_TEXTO);}
<YYINITIAL> "\"AREA_TEXTO\""                {return symbol(CLASS_AREA_TEXTO);}
<YYINITIAL> "\"CHECKBOX\""                  {return symbol(CLASS_CHECKBOX);}
<YYINITIAL> "\"RADIO\""                     {return symbol(CLASS_RADIO);}
<YYINITIAL> "\"FICHERO\""                   {return symbol(CLASS_FICHERO);}
<YYINITIAL> "\"COMBO\""                     {return symbol(CLASS_COMBO);}




<YYINITIAL> {
    ":"                                 {return symbol(COLON);}
    ","                                 {return symbol(COMMA);}
    "{"                                 {return symbol(OPEN_BRACE);}
    "}"                                 {return symbol(CLOSE_BRACE);}
    "("                                 {return symbol(OPEN_ROUND_BRACKET);}
    ")"                                 {return symbol(CLOSE_ROUND_BRACKET);}
    (\s)                                {/*Ignorar*/}
}

<YYINITIAL> {ID}                        {return symbol(ID);}
<YYINITIAL> {FECHA}                     {return symbol(FECHA);}
<YYINITIAL> {ENTERO}                    {return symbol(ENTERO);}
<YYINITIAL> {OPCIONES}                  {return symbol(OPCIONES);}
<YYINITIAL> {OPCIONES}                  {return symbol(OPCIONES2);}
<YYINITIAL> {VALUE}                     {return symbol(VALUE);}
<YYINITIAL> {LITERAL}                   {return symbol(LITERAL);}

[^]                                     {System.out.println("Error " + yytext());}