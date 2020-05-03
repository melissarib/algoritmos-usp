#ifndef STACKTEMPLATE_H
#define STACKTEMPLATE_H

#include <iostream>
#include <cstdlib>
#include <sstream>
using namespace std;

template< class StackEntry >
class Stack
{ public:
    Stack();
    bool empty();
    bool full();
    void push(StackEntry x);
    void pop(StackEntry &x);
    void getTop(StackEntry &x);
    int size();
    void clear();
    string toString();
  private:
    static const int MaxStack = 100;
    int top;                       // topo da pilha
    StackEntry entry[MaxStack+1];  // vetor com elementos
};

template< class StackEntry >
Stack<StackEntry>::Stack()
// pre-condicao: nenhuma
// pos-condicao: Pilha eh criada e iniciada como vazia
{
  top = 0;
}

template< class StackEntry >
bool Stack<StackEntry>::empty()
// pre-condicao: Pilha ja tenha sido criada
// pos-condicao: funcao retorna true se a pilha esta vazia; false caso contrario
{
  return (top == 0);
}

template< class StackEntry >
bool Stack<StackEntry>::full()
// pre-condicao: Pilha ja tenha sido criada
// pos-condicao: funcao retorna true se a pilha esta cheia; false caso contrario
{
  return (top == MaxStack);
}

template< class StackEntry >
void Stack<StackEntry>::push(StackEntry x)
// pre-condicao: Pilha S ja tenha sido criada
// pos-condicao: O item x eh armazenado no topo da pilha
{
  if (full())
  { cout << "Pilha Cheia" << endl;
    abort();
  }
  top++;
  entry[top] = x;
}

template< class StackEntry >
void Stack<StackEntry>::pop(StackEntry &x)
// pre-condicao: Pilha ja tenha sido criada e n�o esta vazia
// pos-condicao: O item no topo da pilha eh removido e seu valor retornado na variavel x
{
  if (empty())
  { cout << "Pilha Vazia" << endl;
    abort();
  }
  x = entry[top];
  top--;
}

template< class StackEntry >
void Stack<StackEntry>::getTop(StackEntry &x)
// pre-condicao: Pilha ja tenha sido criada e n�o esta vazia
// pos-condicao: O item no topo da pilha eh retornado na variavel x; pilha permanece inalterada
{
  if (empty())
  { cout << "Pilha Vazia" << endl;
    abort();
  }
  x = entry[top];
}



template< class StackEntry >
int Stack<StackEntry>::size()
// pre-condicao: Pilha ja tenha sido criada
// pos-condicao: retorna o numero de elementos da pilha
{  return top;
}


template< class StackEntry >
void Stack<StackEntry>::clear()
// pre-condicao: Pilha ja tenha sido criada
// pos-condicao: Esvazia o conteudo da pilha
{  top = 0;
}

template< class StackEntry >
string Stack<StackEntry>::toString()
// pre-condicao: Pilha ja tenha sido criada
// pos-condicao: Transforma conteudo em uma string separada por espacos (Topo a direita)
{  int i;
   stringstream ss;
   
   for(i=1; i<=top; i++)
       ss << entry[i];
   return ss.str();       
}

#endif /* STACKTEMPLATE_H */
