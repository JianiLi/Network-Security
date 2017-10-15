#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  int authorization_level;
  char *username;
} User;

User *login() {
  User *user = malloc(sizeof(User));
  user->authorization_level = 50; 
  user->username = malloc(100);
  printf("Enter username:\n");
  gets(user->username);
  return user;
}

void problem5() {
  FILE *fin;
  char buffer[64];
  uint64_t minimum_level = 200; // 17
  User *user;
  
  user = login();  
  printf("Welcome ");
  printf("%s", user->username);
  printf("!\n");
  
  if (user->authorization_level < minimum_level) {
    printf("Your authorization level is only %d.\n", user->authorization_level);
  }
  else {
    fin = fopen("/home/netsec/secret5.txt", "rt");
    fgets(buffer, sizeof(buffer), fin);
    fclose(fin);
    printf(buffer);
  }    
}

int main() {
  problem5();
}

