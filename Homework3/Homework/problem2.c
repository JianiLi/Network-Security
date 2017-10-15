#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

void processCommand(uint8_t cmd) {
  char buffer[128];
  FILE* fin;
  
  switch(cmd) {
    case 200: // exit
      printf("Goodbye!\n");
      exit(0);
    case 1: // read secret
      fin = fopen("/home/netsec/secret2.txt", "rt");
      fgets(buffer, sizeof(buffer), fin);
      fclose(fin);
      printf(buffer);
      break;
    case 2: // write secret
      printf("Sorry, this has not been implemented yet.\n");
      break;       
    case 101: // read public
      fin = fopen("/home/netsec/public.txt", "rt");
      fgets(buffer, sizeof(buffer), fin);
      fclose(fin);
      printf(buffer);
      break;
    case 102: // write public
      printf("Sorry, this has not been implemented yet.\n");
      break;       
    default:
      printf("Unknown command: %d\n", cmd);
  }
}

void problem2() {
  char buffer[128];
  int command;  
  
  while (1) {
    printf("Select command:\n");
    gets(buffer);
    sscanf(buffer, "%d", &command);  
    if (command < 100)
      printf("You are not authorized to access this file!\n");
    else
      processCommand(command);   
  }    
}

int main() {
  printf("Available commands: \n 1 - read secret file \n 2 - write secret file \n 101 - read public file \n 102 - write public file \n 200 - exit \n");
  problem2();
}

