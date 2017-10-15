#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

void problem3() {
  FILE *fin;
  char *input;
  uint64_t length = 100;
  char *secret;

  secret = malloc(length);
  fin = fopen("/home/netsec/secret3.txt", "rt");
  fgets(secret, length, fin);
  fclose(fin);

  printf("Please enter your guess:\n");
  input = malloc(length);
  gets(input);
  if (strcmp(input, secret) == 0)
    printf("Congratulations, you have guessed the secret!\n");
  else {
    printf("Your guess was ");
    printf(input);
    printf("\n");
  }
}

int main() {
  problem3();
}

