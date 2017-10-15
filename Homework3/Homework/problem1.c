#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

void problem1() {
  char dataset[16];
  char command[16];
  char buffer[128];
  char *filename;

  printf("%lx,%lx\n",(long)dataset,(long)command);
  printf("Enter dataset name (secret or public):\n");
  gets(dataset);
  
  switch(dataset[0]) {
    case 's':
      printf("You are not authorized to use this dataset!\n");
      return;
    case 'p':
      printf("Access granted to the public dataset!\n");
      break;
    default:
      printf("Unknown dataset!\n");
      return;
  }
    
  while (1) {
    printf("Enter command (read, write, or exit):\n");
    gets(command);
    
    switch(command[0]) {
      case 'r': // read
        switch(dataset[0]) {
          case 's':
            //filename = "/home/netsec/secret1.txt";
            filename = "/Users/jianili/Downloads/Homework3 2/ecc.txt";
            break;
          case 'p':
            //filename = "/home/netsec/public.txt";
            filename = "/Users/jianili/Downloads/Homework3 2/err.txt";
            break;
        }
        FILE* fin = fopen(filename, "rt");
        fgets(buffer, sizeof(buffer), fin);
        fclose(fin);
        printf(buffer);
        break;
        
      case 'w': // write
        printf("Sorry, this has not been implemented yet.\n");
        break;       
         
      case 'e': // exit
        printf("Goodbye!\n");
        return;
   
      default:
        printf("Unknown command!\n");
    }       
  }    
}

int main() {
  problem1();
}

