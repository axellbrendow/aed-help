#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *file = fopen("numbers.txt", "w");
    int numNumbers;
    float number;

    scanf("%d", &numNumbers);

    for (int i = 0; i < numNumbers; i++)
    {
        scanf("%f", &number);
        fwrite(&number, sizeof(float), 1, file);
    }

    fclose(file);

    file = fopen("numbers.txt", "r");

    for (
        long deslocamento = (numNumbers - 1) * sizeof(float);
        deslocamento >= 0;
        deslocamento -= sizeof(float))
    {
        fseek(file, deslocamento, SEEK_SET);
        fread(&number, sizeof(float), 1, file);
        printf("%g\n", number);
    }

    fclose(file);

    return 0;
}
