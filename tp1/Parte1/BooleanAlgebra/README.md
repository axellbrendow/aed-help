All the .c files in this folder are boolean expression interpreters.

The input they expect is of the form: &lt;number_of_operands&gt; &lt;operand1&gt; &lt;operand2&gt; ... &lt;operandn&gt; &lt;expression&gt;. Where the operands are the logic values 0 or 1 and the expression is like: and( or(A, B), not(C) ) where A, B and C refer to the operands 1, 2 and 3 respectively.

One example of a valid input: 3 1 1 1 and( or(A, B), not(C) )

These are the railroad diagrams that represents what the interpreters can interpret:

Operand:
![Railroad diagram for operands](https://i.imgur.com/lNYA5cD.png)

Operator:
![Railroad diagram for operators](https://i.imgur.com/njFo9o0.png)

Operation:
![Railroad diagram for operation](https://i.imgur.com/cbUCLRd.png)

These images can be found on the interpreter_images folder. [Here](http://www.bottlecaps.de/rr/ui) is the tool used to create these diagrams.
