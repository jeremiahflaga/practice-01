using Xunit;

namespace SieveOfEratosthenesKata.Tests
{
    public class PrimeGeneratorTest
    {
        [Fact]
        public void Test2()
        {
            int[] primes = PrimeGenerator.generatePrimes(2);

            Assert.Equal(1, primes.Length);
        }

        [Fact]
        public void Test3()
        {
            int[] primes = PrimeGenerator.generatePrimes(3);

            Assert.Equal(2, primes.Length);
        }
    }
}
