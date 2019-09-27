/**
 * Define a grammar called Hello
 */
grammar Hello;

// LEXER RULES
OP : '=='| '!=' | '<' | '>' | '<=' | '>='; 
ALPHA : ( 'a' .. 'z' | 'A' .. 'Z' | '_');
DIGIT: '0'..'9';  
INTEGER : DIGIT { DIGIT };
IDENTIFIER : ALPHA { ( ALPHA | DIGIT ) };
STRING_LITERAL : ( ALPHA | DIGIT )+;
WS : [ \t\r\n]+ -> skip ;

// PARSER RULES

// BATCH 1
literal : STRING_LITERAL | INTEGER;
relation_name : IDENTIFIER;
attribute_name : IDENTIFIER;
operand : attribute_name | literal;
type : VARCHAR ( integer ) | INTEGER;
attribute_list : attribute-name { , attribute-name }; 
typed_attribute_list : attribute_name type { , attribute-name type };
open_cmd : OPEN relation_name ;
close_cmd :CLOSE relation_name;
write_cmd :WRITE relation_name ;
exit_cmd :EXIT;

// BATCH 2

