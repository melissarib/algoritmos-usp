/*
 * List.h
 *
 *  Created on:
 *      Author: MANOELA BARRETO DE OLIVEIRA REIS
 */

#ifndef LIST_H
#define LIST_H

#include <iostream>
#include <cstring>
#include <cstdlib>
#include <sstream>
using namespace std;

typedef long ListEntry;

class List
{ public:
    List();
    ~List();
    bool empty();
    bool full();
    void clear();
    long size();
    void insert(long p, ListEntry x);
    void Delete(long p, ListEntry &x);
    void retrieve(long p, ListEntry &x);
    long search(ListEntry x);
    string toString();
    string toStringAddr();
    long long getAddr(long p);

    bool swap(ListEntry a, ListEntry b);

  private:
    // declaracao de tipos
    struct ListNode;

    typedef ListNode *ListPointer;

    struct ListNode
    { ListEntry   entry;   	    // tipo de dado colocado na lista
      ListPointer nextNode;   	// ligacao para proximo elemento na lista
    };

    // campos
    ListPointer head;           // inicio da lista
    long count;                 // numero de elementos

    // metodos privados
    void setPosition(long p, ListPointer &current);
};

#endif /* LIST_H */
