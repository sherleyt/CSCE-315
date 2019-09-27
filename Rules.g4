/**
 * Define a grammar called Rules
 */
grammar Rules;

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
condition : conjunction { || conjunction };
conjunction :comparison { && comparison };
comparison : operand 'op' operand | ( condition );

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

// BATCH 4
show_cmd : 'SHOW' atomic_expr;
create_cmd : 'CREATE TABLE' relation_name ( typed_attribute_list ) 
				'PRIMARY KEY' ( attribute_list );
update_cmd : 'UPDATE' relation_name 'SET' attribute_name '=' literal { , 
				attribute_name = literal } 'WHERE' condition;
insert_cmd : 'INSERT INTO' relation_name 'VALUES FROM' ( literal { , 
				literal } ) 
				| 'INSERT INTO' relation_name 'VALUES FROM RELATION' expr;
delete_cmd : 'DELETE FROM' relation_name 'WHERE' condition;

// BATCH 5
command : ( open_cmd | close_cmd | write_cmd | exit_cmd | show_cmd | 
			create_cmd | update_cmd | insert_cmd | delete_cmd ) ;
query : relation_name '<-' expr;
program : { ( query | command ) };
