using Xunit;

namespace SieveOfEratosthenesKata.Tests
{
    public class PrimeGeneratorTest
    {
        [Fact]
        public void Test()
        {
            int[] primes = PrimeGenerator.generatePrimes(2);

            Assert.Equal(1, primes.Length);
        }
    }
}
