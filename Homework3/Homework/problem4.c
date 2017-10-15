#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#include <stat.h>

void problem4() {
  char buffer[100];
  struct stat m;
  stat("public.txt", &m);

  if(!S_ISLNK(m.st_mode)){
	  FILE *fin = fopen("public.txt", "rt");
	  fgets(buffer, 100, fin);
	  fclose(fin);
	  
	  printf("The public data is:\n %s\n", buffer);
	}
	else{
		printf("error");
	}
}

int main() {
  problem4();
}

