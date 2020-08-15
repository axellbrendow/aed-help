All the .c files in this folder are boolean expression interpreters.

The input they expect is of the form: &lt;number_of_operands&gt; &lt;operand1&gt; &lt;operand2&gt; ... &lt;operandn&gt; &lt;expression&gt;. Where the operands are the logic values 0 or 1 and the expression is like: and( or(A, B), not(C) ) where A, B and C refer to the operands 1, 2 and 3 respectively.

One example of a valid input: 3 1 1 1 and( or(A, B), not(C) )

These are the railroad diagrams that represents what the interpreters can interpret:

Operand:

<img alt="Railroad diagram for Operand" src="./interpreter_images/Operand"></img>

Operator:

<img alt="Railroad diagram for Operator" src="./interpreter_images/Operator"></img>

Operation:

<img alt="Railroad diagram for Expression" src="./interpreter_images/Expression"></img>

Grammar:

```
Operand ::= 'A' | 'B' | 'C'

Operator ::= 'not' | 'and' | 'or'

Expression ::= Operand | Operator '(' Expression (',' Expression)* ')'
```

These images can be found on the interpreter_images folder. [Here](http://www.bottlecaps.de/rr/ui) is the tool used to create these diagrams.
