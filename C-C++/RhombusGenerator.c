
#include <stdio.h>
#include <stdlib.h>

/*
 *
 */
int main() {


    int value = 0;
    while (value < 1 || value > 9) {
        printf("Please enter a value between 1 and 9 : \n");
        scanf(" %d", &value);
    }
        int spaces = value -1;
        int rowNum = 1;

        /* Create top half */
        for (int i = 0; i < value; i++) {
            createRow(spaces, value, rowNum);
            rowNum++;
            spaces--;
        }

        /* Create bottom half*/
         spaces = 1;
         rowNum = value -1;
        for (int j = 0; j < value - 1; j++) {
            createRow(spaces, value, rowNum);
            rowNum--;
            spaces++;
        }
        return (EXIT_SUCCESS);


}

int createRow (int spaces, int value, int rowNum) {
    /* Creates spaces based on parameter given*/
    for (int i = 0; i < spaces; i++) {
        printf(" ");
    }
    /* Creates actual row based on rowNumber */
    for (int j = 1; j <= rowNum; j++) {
        printf("%d",j);
        if (j == rowNum) {
            int k = 1;
            while (j-k > 0) {
                printf("%d", j-k);
                k++;
            }

        }
    }
    printf("\n");

}
