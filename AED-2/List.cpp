/*
 *
 *  Created on:
 *      Author: MANOELA BARRETO DE OLIVEIRA REIS
 * 		Status: Incomplete
 */
#include "List.h"
//---------------------------------------------------------------
List::List()
{
  head = NULL;
  count = 0;
}
//---------------------------------------------------------------
List::~List()
{
  clear();
}
//---------------------------------------------------------------
bool List::empty()
{
  return (head == NULL);
}
//---------------------------------------------------------------
bool List::full()
{
   return false;
}
//---------------------------------------------------------------
void List::clear()
{ ListPointer q;

  while (head != NULL)
  {  q = head;
     head = head->nextNode;
     delete q;
  }
  count = 0;
}
//---------------------------------------------------------------
long List::size()
{
   return count;
}
//---------------------------------------------------------------
void List::insert(long p, ListEntry x)
{ ListPointer newNode, current;

  if (p < 1 || p > count+1)
  { cout << "Posicao invalida" << endl;
    abort();
  }
  newNode = new ListNode;
  newNode->entry = x;
  if(p == 1)
  {  newNode->nextNode = head;
     head = newNode;
  }
  else
  {  setPosition(p-1,current);
     newNode->nextNode = current->nextNode;
     current->nextNode = newNode;
  }
  count++;
}
//---------------------------------------------------------------
void List::Delete(long p, ListEntry &x)
{ ListPointer node, current;

  if (p < 1 || p > count)
  { cout << "Posicao invalida" << endl;
    abort();
  }
  if(p == 1)
  {  node = head;
     head = node->nextNode;
  }
  else
  {  setPosition(p-1,current);
     node = current->nextNode;
     current->nextNode = node->nextNode;
  }
  x = node->entry;
  delete node;
  count = count - 1;
}
//---------------------------------------------------------------
void List::retrieve(long p, ListEntry &x)
{ ListPointer current;

  setPosition(p,current);
  x = current->entry;
}
//---------------------------------------------------------------
long List::search(ListEntry x)
{ long p=1;
  ListPointer q=head;

  while (q != NULL && q->entry != x)
  {  q = q->nextNode;
     p++;
  }
  return (q == NULL ? 0 : p);
}
//---------------------------------------------------------------
string List::toString()
{ ListPointer q=head;
  string s;
  stringstream ss;

  while (q != NULL)
  {  ss << q->entry << ",";
     q = q->nextNode;
  }
  s = ss.str();
  return "[" + s.substr(0,s.length()-1) + "]";
}
//---------------------------------------------------------------
string List::toStringAddr()
{ ListPointer q=head;
  string s;
  stringstream ss;

  while (q != NULL)
  {  ss << q << ",";
     q = q->nextNode;
  }
  s = ss.str();
  return "[" + s.substr(0,s.length()-1) + "]";
}
//---------------------------------------------------------------
void List::setPosition(long p, ListPointer &current)
{ long i;

  if (p < 1 || p > count+1)
  { cout << "Posicao invalida" << endl;
    abort();
  }
  current = head;
  for(i=2; i<=p; i++)
      current = current->nextNode;
}
//---------------------------------------------------------------
long long List::getAddr(ListEntry x)
{ ListPointer current=NULL;
  long p = search(x);
  if(p != 0)
    setPosition(p, current);
  return (long long)current;
}
//---------------------------------------------------------------
bool List::swap(ListEntry a, ListEntry b)
{ 
	if(empty() || size() == 1 || a==b){ //Se a lista est� vazia ou cont�m um �nico elemento ou a=b, o m�todo n�o altera a lista e retorna false;
		//cout<<"n�o haver� altera��o na listas"
		return (false);
	}else{
		
		bool A_presente = false;
		bool B_presente = false;
		bool A_comeco = false;
		bool B_comeco = false;
		
		ListPointer anteriorA; // ponteiro para marcar o n� antes de onde o a est�
		ListPointer n; // ponteiro para marcar o n� exatamene onde o a est�
		ListPointer nBackupNext;// ponteiro para marcar o elemento depois de onde o a est�
		
		ListPointer anteriorB; // ponteiro para marcar o elemento antes de onde o a est�
		ListPointer n2; // ponteiro para marcar o n� exatamene onde o b est�		
		
		ListPointer q = head;
		
		while(q!=NULL){
			
			if(q->nextNode !=NULL){ // este if no geral serve para verificar se cada posi��o da lista � o elemento que se est� procurando. E dependendo se est� no in�cio ou n�o s�o setados ponteiros de maneira diferenciada para cada elemento
				if(head->entry == a && !A_comeco){ 
					A_comeco = true;				
				}else if(head->entry == b && !B_comeco){
					B_comeco = true;
				} 
				if(q->nextNode->entry == a && !A_presente && !A_comeco){
					anteriorA = q;
					n = anteriorA->nextNode;
					nBackupNext = n->nextNode;
					A_presente = true;
				}
				if(q->nextNode->entry == b && !B_presente && !B_comeco){
					anteriorB = q;
					n2 = anteriorB->nextNode;
					B_presente = true;	
				}
			}						
		
			q = q->nextNode;
			
			if(B_presente && A_presente){//se os dois elementos est�o presentes na lista
				if(n2->nextNode == NULL && n->nextNode->entry == b){//testa se � adjacente com a antes de b e se b � o �ltimo da lista
					anteriorA->nextNode = n2;
					n->nextNode = NULL;
					n2->nextNode = n;
					return (true);
				}else if(n2->nextNode == NULL){//testa s� se b � o ultimo da lista
					anteriorA->nextNode = anteriorB->nextNode;			          
					n->nextNode = NULL;			    
					anteriorB->nextNode = n;
					n2->nextNode = nBackupNext;
					return (true);
				}else if(n2->nextNode->entry == a){ //adjac�ncia com b antes de a
					anteriorB->nextNode = n;
					n2->nextNode = n->nextNode;
					n->nextNode = n2;
					return (true);
				}else if(n->nextNode->entry == b){//adjac�ncia com a antes de b
					anteriorA->nextNode = n2;
					n->nextNode = n2->nextNode;
					n2->nextNode = n;
					return (true);
				}else{ //n�s-alvo separados por pelo menos um n� no meio deles
					anteriorA->nextNode = anteriorB->nextNode;			          
					n->nextNode = n2->nextNode;			    
					anteriorB->nextNode = n;
					n2->nextNode = nBackupNext;
					return (true);
				}
				
			} else if(A_comeco && B_presente){//se o elemento a est� no in�cio e b est� em qualquer outra posi��o da lista
					n = head;				
					nBackupNext = n->nextNode;
					if(n->nextNode->entry == b){ //adjac�ncia com a no come�o antes de b
						head = nBackupNext;
						n->nextNode = n2->nextNode;
						n2->nextNode=n;
						return (true);
					}else{ //a no come�o e pelo menos um n� entre a e b
						head = anteriorB->nextNode;
						n->nextNode = n2->nextNode;			    
						anteriorB->nextNode = n;
						n2->nextNode = nBackupNext;
						return (true);
					}			
					
					
			} else if(B_comeco && A_presente){//se o elemento b est� no in�cio e b est� em qualquer outra posi��o da lista
					n2 = head;
					if(n2->nextNode->entry == a){ //adjac�ncia com b no come�o antes de a
						head = n;
						n2->nextNode= n->nextNode;
						n->nextNode=n2;
						return (true);
					}else{ //b no come�o e pelo menos um n� entre b e a
						head = anteriorA->nextNode;
					    n->nextNode = n2->nextNode;			    
						anteriorA->nextNode = n2;
						n2->nextNode = nBackupNext;
					    return (true);
					}
				    
			} if(q == NULL){ //SE eu cheguei no fim da lista e nenhuma das condi��es para troca foram atendidas; isso significa que os elementos n�o foram encontrados
				//cout<<endl<<"Os elementos pedidos nao estao na lista"<<endl;
				return (false);
			}	
		}
		return (false); // coloquei isso aqui s� porque sem isso o web cat falava q n retornava nada 
	}
	return (false);// coloquei isso aqui s� porque sem o web cat falava q n retornava nada 
}
//---------------------------------------------------------------
