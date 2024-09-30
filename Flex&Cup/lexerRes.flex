package com.response.analizador.lexico;

import static com.response.analizador.sintactico.ResponseSym.*;
import java_cup.runtime.Symbol;

%%

%class ResponseLexer
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

VALUE = "\""[^\s"\""]*"\""
LITERAL = "\""[^"\""]*"\""

%%

<YYINITIAL> "envio_respuesta"             {return symbol(START_RESPONSE);}
<YYINITIAL> "fin_envio_respuesta"             {return symbol(END_RESPONSE);}
<YYINITIAL> "envio_respuestas"            {return symbol(START_RESPONSES);}
<YYINITIAL> "fin_envio_respuestas"            {return symbol(END_RESPONSES);}

<YYINITIAL> "\"RESPUESTA_SERVIDOR\""       {return symbol(SERVER_RESPONSE);}
<YYINITIAL> "\"PARAMETROS_RESPUESTA\""  {return symbol(RESPONSE_PARAMS);}

<YYINITIAL> "\"MESSAGE\""               {return symbol(PARAM_MESSAGE);}
<YYINITIAL> "\"LOGGED_USER\""           {return symbol(PARAM_LOGGED_USER);}

<YYINITIAL> {
    "<"                                 {return symbol(LESS_THAN);}
    ">"                                 {return symbol(GREATER_THAN);}
    "!"                                 {return symbol(EXCLAMATION_MARK);}
    ":"                                 {return symbol(COLON);}
    ","                                 {return symbol(COMMA);}
    "{"                                 {return symbol(OPEN_BRACE);}
    "}"                                 {return symbol(CLOSE_BRACE);}
    "["                                 {return symbol(OPEN_BRACKET);}
    "]"                                 {return symbol(CLOSE_BRACKET);}
    (\s)                                {/*Ignorar*/}
}

<YYINITIAL> {VALUE}                     {return symbol(VALUE);}
<YYINITIAL> {LITERAL}                     {return symbol(LITERAL);}

[^]                                     {System.out.println("Error responseLexer: " + yytext());}