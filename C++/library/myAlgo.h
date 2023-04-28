#include <iostream>
#ifndef _MYALGO
#define _MYALGO

void shellSort(int* array, int len)
{
    for (int s = len / 2; s > 0; s /= 2)
    {
        for (int i = s; i < len; i++)
        {
            for (int j = i - s; j >= 0 && array[j] > array[j + s]; j -= s)
            {
                int temp = array[j];
                array[j] = array[j + s];
                array[j + s] = temp;
            }
        }
    }
}

int binSearch(int* arrPtr, int len, int target, bool isSorted)
{
    int* arr = arrPtr;
    if (!isSorted) shellSort(arr, len);

    int l = 0, r = len - 1;
    while (l < r)
    {
        int mid = l + (r - l) / 2;
        int num = arr[mid];
        if (num == target)
            return mid;
        else if (num < target)
            l = mid + 1;
        else
            r = mid;
    }
    return -1;
}

unsigned long long fastExp(int num, int exp)
{
    if (exp < 0 || num < 0)
    {
        throw std::invalid_argument("Exponent should be positive!");
    }
    unsigned long long ans = 1, m = num;
    while (exp)
    {
        if (exp % 2)
            ans *= m;
        m *= m;
        exp >>= 1;
    }
    return ans;
}

#endif // !_myalgo

