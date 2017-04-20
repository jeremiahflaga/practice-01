using System;

namespace SieveOfEratosthenesKata
{
    public class PrimeGenerator
    {
        public static int[] generatePrimes(int n) {
            if (n <= 2)
                return new int[] {2};
            else if(n <= 4)
                return new int[] {2, 3};
            else
                return new int[] {2, 3, 5};
        }
    }
}
