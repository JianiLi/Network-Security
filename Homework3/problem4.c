#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

void problem4() {
  char buffer[100];

  FILE *fin = fopen("public.txt", "rt");
  fgets(buffer, 100, fin);
  fclose(fin);
  
  printf("The public data is:\n %s\n", buffer);
}

int main() {
  problem4();
}

