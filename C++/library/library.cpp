#include <iostream>
#include "myAlgo.h"

int main()
{
    int arr[20];
    for (int i = 0; i < 20; i++)
        arr[i] = rand() % 10;

    
    for (int i = 0; i < 20; i++)
        std::cout << arr[i] << ' ';
    std::cout << std::endl;


    int arrLen = sizeof(arr) / sizeof(*arr);
    std::cout << binSearch(arr, arrLen, 5, false) << std::endl;

    try
    {
        std::cout << "5 ^ 10 = " <<  fastExp(5, 10);
    }
    catch (std::exception e)
    {
        std::cerr << e.what();
        return 1;
    }
    return 0;
}