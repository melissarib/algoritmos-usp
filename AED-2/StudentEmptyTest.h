#ifndef STUDENTEMPTYTEST_H
#define STUDENTEMPTYTEST_H

#include <cxxtest/TestSuite.h>

class StudentEmptyTest : public CxxTest::TestSuite
{
  public:
    void testVazio()
    {
    }
    /**
     * Este arquivo contem um teste vazio. Eh necessario
     * para que a plataforma Web-CAT possa avaliar adequadamente
     * seu programa. 
     * 
     * Dessa forma, este arquivo sempre deve ser submetido
     * ao Web-CAT junto com os demais arquivos do seu projeto 
     * (*.h e *.cpp) no arquivo compactado (formato ZIP).
     *
     * A omissao desse arquivo na sua submissao resulta em
     * atribuicao da nota ZERO ao seu programa pela plataforma
     * Web-CAT.
     * 
     * Em caso de duvida, procure o professor antes do termino
     * do prazo para a respectiva atividade junto ao Web-CAT.
     *    
     * Caso tenha interesse em incluir seus testes, consulte
     * http://cxxtest.com/guide.html#testAssertions
     * Nesse caso, tenha certeza que seu programa passa em todos
     * os seus testes de forma a obter a nota maxima possivel
     * na atividade.
     */
};

#endif
