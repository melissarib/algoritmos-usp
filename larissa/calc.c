#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    int num1, num2, soma, subtracao, multi, div;

    printf("Calculadora \n");
    printf("Entre  com 2 numeros: ");
    scanf("%d%d", &num1, &num2);

    soma            = num1 + num2;
    subtracao       = num1 - num2;
    multi           = num1 * num2;
    div             = num1 / num2;

    printf( "A soma e: %d\n", soma );
    printf( "A subtracao e: %d\n", subtracao );
    printf( "O produto e: %d \n", multi );
    printf( "A divisão e: %d \n", div );

    return 0;
}