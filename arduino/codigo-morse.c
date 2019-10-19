/*
  Este programa se comporta como um sistema similar
  ao Codigo Morse, com LED
  
  Licença: dominio publico
  Autor: J Cesar Bertelli
  
  0  representa pisca curto,
  1  pisca longo,
  7  fim de letra,
  13 fim de palavra.
*/

#define MORSE_A {0, 1, 7, 7, 7}
#define MORSE_L {0, 1, 0, 0, 7}
#define MORSE_O {1, 1, 1, 7, 7}
#define MORSE_ALO {MORSE_A, MORSE_L, MORSE_O, {13}}

char morse_alo[][5] = MORSE_ALO;
String palavra = "ALO";

void setup()
{
  pinMode(13, OUTPUT);
  Serial.begin(9600);
}

void loop()
{
  pisca_palavra(morse_alo);
  Serial.print("Terminei a palavra: ");
  Serial.println(palavra);
  delay(7000);
}

void pisca_base(unsigned long tempo) {
  Serial.println("Entrei na funcao PISCA_BASE");
  digitalWrite(13, HIGH);
  delay(tempo/2);
  digitalWrite(13, LOW);
}

void pisca_letra(char morse_letra[]) {
  Serial.println("Entrei na funcao PISCA_LETRA");
  for (int i = 0; morse_letra[i] != 7; i++) {
    if (i != 0) delay(1000);
    if (morse_letra[i] == 0) {
      Serial.println("Sinal curto");
      pisca_base(1000);
    } else {
      Serial.println("Sinal longo");
      pisca_base(3000);
    }
    
  }
  Serial.println("Terminei a letra (7)");
}

void pisca_palavra(char morse_palavra[][5]) {
  Serial.println("Entrei na funcao PISCA_PALAVRA");
  for (int i = 0; morse_palavra[i][0] != 13; i++) {
    if (i != 0) delay(3000);
    pisca_letra(morse_palavra[i]);
  }
  Serial.println("Terminei a palavra (13)");
}
