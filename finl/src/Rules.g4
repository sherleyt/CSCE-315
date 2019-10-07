//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
/** 
 *                    //Define all Rules. mostly defining syntax on how they should show up
 */
grammar Rules;

// LEXER RULES
//Define these for later obvious uses
OP : '=='| '!=' | '<' | '>' | '<=' | '>='; 
fragment ALPHA : ( 'a' .. 'z' | 'A' .. 'Z' | '_');
fragment DIGIT: '0'..'9';
INTEGER : ('-')? DIGIT (DIGIT)*;
IDENTIFIER : ALPHA ( ALPHA | DIGIT )*;
STRING_LITERAL : '"' ( ALPHA | DIGIT )+ '"';
WS : [ \t\r\n]+ -> skip ;                                     //Ignore spacings/etc


// PARSER RULES
// BATCH 1 -- 
//Variables
literal : STRING_LITERAL | INTEGER;
relation_name : IDENTIFIER;
attribute_name : IDENTIFIER;
operand : attribute_name | literal;
type : 'VARCHAR' '('INTEGER')' | 'INTEGER';
attribute_list : attribute_name (',' attribute_name )*; 
typed_attribute_list : attribute_name type ( ',' attribute_name type )*;
//Output/input commands
open_cmd : 'OPEN' relation_name ;
close_cmd : 'CLOSE' relation_name;
write_cmd : 'WRITE' relation_name ;
exit_cmd : 'EXIT';

// BATCH 2
LOGICAL_OR: '||';
LOGICAL_AND: '&&';
//Recursive condition breakdown
condition:
    condition LOGICAL_AND condition         //check && and ||
    | condition LOGICAL_OR condition
    | comparison							//check if normal comparison(defined below)
    | '(' condition ')';                   //Check if in parenthesis

comparison: operand (OP operand)?;  //? is optional operator(to check...


// BATCH 3--
//Syntaxs for all the expressions and how they show up
expr         : atomic_expr | selection | projection | renaming | union | difference | product | natural_join;
atomic_expr  : relation_name | '('expr')';
selection    : 'select' '('condition')' atomic_expr;  
projection   : 'project' '('attribute_list')' atomic_expr;
renaming     : 'rename' '('attribute_list')' atomic_expr;
union        : atomic_expr '+' atomic_expr;
difference   : atomic_expr '-' atomic_expr;
product      : atomic_expr '*' atomic_expr;
natural_join : atomic_expr '&' atomic_expr;

// BATCH 4--
//Syntaxs for all the commands have to follow this
show_cmd : 'SHOW' atomic_expr;
create_cmd : 'CREATE TABLE' relation_name '(' typed_attribute_list ')'
				'PRIMARY KEY' '(' attribute_list ')';
update_cmd : 'UPDATE' relation_name 'SET' attribute_name '=' literal ( ','
				attribute_name '=' literal )* 'WHERE' condition;
insert_cmd : 'INSERT INTO' relation_name 'VALUES FROM' '(' literal ( ','
				literal )* ')'
				| 'INSERT INTO' relation_name 'VALUES FROM RELATION' expr;
delete_cmd : 'DELETE FROM' relation_name 'WHERE' condition;


// BATCH 5--
//Group all commands
command : ( open_cmd | close_cmd | write_cmd | exit_cmd | show_cmd |
			create_cmd | update_cmd | insert_cmd | delete_cmd ) ';';
//Query syntax
query : relation_name '<-' expr;
program : (  query | command  )*;
