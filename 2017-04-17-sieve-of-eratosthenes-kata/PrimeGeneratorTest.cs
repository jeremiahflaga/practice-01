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
            Assert.Equal(2, primes[0]);
        }

        [Fact]
        public void Test3()
        {
            int[] primes = PrimeGenerator.generatePrimes(3);

            Assert.Equal(2, primes.Length);
            Assert.Equal(2, primes[0]);
            Assert.Equal(3, primes[1]);
        }

        [Fact]
        public void Test4()
        {
            int[] primes = PrimeGenerator.generatePrimes(4);

            Assert.Equal(2, primes.Length);
            Assert.Equal(2, primes[0]);
            Assert.Equal(3, primes[1]);
        }

        [Fact]
        public void Test5()
        {
            int[] primes = PrimeGenerator.generatePrimes(5);

            Assert.Equal(3, primes.Length);
            Assert.Equal(2, primes[0]);
            Assert.Equal(3, primes[1]);
            Assert.Equal(5, primes[2]);
        }
    }
}
