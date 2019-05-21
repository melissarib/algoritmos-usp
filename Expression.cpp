/* 
  *******************************************
  * Departamento de Computacao e Matematica *
  * Algoritmos e Estruturas de Dados 1: IBM *
  * Melissa Augusto Ribeiro                 *
  * Contato: melissarib@hotmail.com         *
  *******************************************
*/                    

#include "Expression.h"
#include <math.h>

using namespace std;
/*
int main()
{
   cout << "**************************************" << endl;
   cout << "* Reverse Polish Notation Calculator *" << endl;
   cout << "**************************************" << endl;
   Expression e(" 1");
   cout << "The expression u evaluated was: " + e.getInfix() << endl;
   cout << "In Reverse Polish Notation: " + e.getPostfix() << endl;
   cout << "Arithmetic account result: " << e.getValue() << endl;

   return 0;
}*/

// pre-condition: receive the operator to calculate precedence
// post-condition: return the precedence value of the operator.
// an operator with greater weight will have greater precedence.
int arithmeticPrecedence(char operation)
{
   int overweight = -1;
   switch (operation)
   {
   // lower precedence operators
   case '+':
   case '-':
      overweight = 1;
      break;
   // intermediate precedence operators
   case '*':
   case '/':
   case '%':
      overweight = 2;
      break;
   // operators with maximum precedence
   case '^':
      overweight = 3;
      break;
   // default value of precedence
   default:
      overweight = -1;
      break;
   }
   return overweight;
}
// pre-condition: none
// post-condition: Given the expression expression expression, convert it to the post-expression notation expression finds its value
Expression::Expression(string expression)
{
   setInfix(expression);
}

// pre-condition: none
// post-condition: Given the infix expression s, convert it to the postfix notation and find its value
void Expression::setInfix(string expression)
{
   infix = expression;
   infixToPostfix();
   evalPostfix();
}

// pre-condition: created object
// post-condition: return expression in infix notation
string Expression::getInfix()
{
   return infix;
}

// pre-condition: created object
// post-condition: returns the expression in the postfix notation (RPN)
string Expression::getPostfix()
{
   return postfix;
}

// pre-condition: created object
// post-condition: returns the expression in the postfix notation (RPN)
int Expression::getValue()
{
   return value;
}

// pre-condition: infix contains a valid arithmetic expression, containing operators and digits in the infographic notation
// post-condition: postfix contains the same expression given by infix, converted into postfixed notation (RPN)
void Expression::infixToPostfix()
{
   int i, m = infix.length();
   char t, top;

   // pre-condition: none 
   // post-condition: the stack is created and initialized as empty
   Stack<char> stackchar;
   postfix = "";
  
   for (i = 0; i < m; i++)                                              // for i from 1 to m
   {
      if (isdigit(infix[i]))                                            // if Ui is an operand (a number)
      {
         postfix += infix[i];                                           // transfer to postfix string
         //cout << "The digit" << infix [i] << "was added in the postfix string." << endl;
      }

      else if (infix[i] == '(' && !stackchar.full()){                    // if Ui is a left parenthesis:
         stackchar.push(infix[i]);                                       // put Ui on the stack
                                               
         //cout << "left parent" << infix [i] << "successfully placed on the stack." << endl;
         //cout << "Stack size:" << stackchar.size () << endl;
      }

      else if (infix[i] == ')')                                         // if Ui is a right parenthesis
      {
         if (!stackchar.empty())
         {                                                              // if the stack is not empty
            stackchar.getTop(top);                                      // check what is the current top-of-the-stack item

            while (!stackchar.empty() && top != '(')                    // until u find a left parenthesis
            {
               stackchar.pop(top);                                      // remove characters from the top of the stack
               postfix += top;                                          // and transfer to postfix string
               if (!stackchar.empty())
                  stackchar.getTop(top);
            }
         }
         stackchar.pop(top);                                            //remove the left parenthesis from the stack and discard
         //cout << "Left parenthesis discarded successfully." << endl;
      }

      else if (!isdigit(infix[i]) && (infix[i] != '(') && (infix[i] != ')') && (infix[i] != ' ')) 
      {                                                                 // if Ui is an arithmetic operator
         if (!stackchar.empty()){
            stackchar.getTop(t);                                        // assume t as top of the stack
            while (!stackchar.empty() && t != '(' && arithmeticPrecedence(infix[i]) <= arithmeticPrecedence(t))
            {                                                           // verify which precedence is predominant
               stackchar.pop(t);                                        // remove the top
               postfix += t;                                            // add to postfix string
               if (!stackchar.empty())
                  stackchar.getTop(t);
            }
         }
         stackchar.push(infix[i]);
      }
   }

   if (i >= m) {                                                        // if infix size is larger than for
      while (!stackchar.empty())                                        // while the stack is not empty
      {
         stackchar.pop(t);                                              // remove the rest in the stack
         postfix += t;                                                  // and add in the postfix variable
         if (!stackchar.empty())
            stackchar.getTop(t);
      }
   }
}

// pre-condition: receives the value of the operator contained in the postfix and the two values populated
// post-condition: returns the result of the account performed
unsigned int calculationPerformed(char token, int x, int y)
{
   switch(token) {
      // lower precedence operators
      case '+':
         return (x + y);
         break;
      case '-':
         return (x - y); 
         break;
           // intermediate precedence operators
      case '*':
         return (x * y);
         break;
      case '/':
         if (x == 0){
            cout << "Error. Division by zero." << endl;
         }
         else{
            return (x / y);
         }
          break;
      case '%':
         if (x == 0){
            cout << "Error. Rest of division by zero does not exist" << endl;}
         else{
            return (x % y);}
         break;
       // operators with maximum precedence
      case '^':
         return (pow(y, x));
         break;
      // default value of precedence
       default:
         token = -1;
         break;
   }
   return token;
}

// pre-condition: postfix contains expression in postfix notation
// post-condition: returns the value of the expression, using Algorithm 2
void Expression::evalPostfix()
{
   
   int n = postfix.length(), i, convertPostfix, result, x, y;
   value = 0;

   Stack<int> stackint;
   for (i = 0; i < n; i++)
   {
      if (isdigit(postfix[i]))
      {
         convertPostfix = postfix[i] - '0';
         stackint.push(convertPostfix);
      }
      else if (postfix[i] == '+' || postfix[i] == '-' || postfix[i] == '*' || 
               postfix[i] == '/' || postfix[i] == '%' || postfix[i] == '^') // if Ui is an operator
      {
         stackint.pop(x);  // the first number comes after the account
         stackint.pop(y);
         result = calculationPerformed(postfix[i], y, x);
         stackint.push(result);
      }
   }
   if (!stackint.empty())
      stackint.getTop(value);
   else
      value = 0;
}
