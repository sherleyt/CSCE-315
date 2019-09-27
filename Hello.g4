/**
 * Define a grammar called Rules
 */
grammar Rules;

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
type : 'VARCHAR' (INTEGER) | INTEGER;
attribute_list : attribute_name { , attribute_name }; 
typed_attribute_list : attribute_name type { , attribute-name type };
open_cmd : 'OPEN' relation_name ;
close_cmd : 'CLOSE' relation_name;
write_cmd : 'WRITE' relation_name ;
exit_cmd : 'EXIT';

// BATCH 2




// BATCH 3
expr         : atomic_expr | selection | projection | renaming | union | difference | product | natural_join;
atomic_expr  : relation_name | '('expr')';
selection    : 'select' '('condition')' atomic_expr;
projection   : 'project' '('attribute_list')' atomic_expr;
renaming     : 'rename' '('attribute_list')' atomic_expr;
union        : atomic_expr '+' atomic_expr;
difference   : atomic_expr '-' atomic_expr;
product      : atomic_expr '*' atomic_expr;
natural_join : atomic_expr '&' atomic_expr;
