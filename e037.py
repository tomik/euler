
import elib

NUM_TRUNCATABLES = 11

def is_truncatable(num):
    digits = elib.to_digits(num)
    for i in xrange(1, len(digits)):
        if not elib.is_prime(elib.from_digits(digits[i:])) or \
           not elib.is_prime(elib.from_digits(digits[:i])):
            return False
    return True


truncatables = []
found = 0
for prime in elib.primes(10):
    if not is_truncatable(prime):
        continue
    found += 1
    truncatables.append(prime)
    if found == NUM_TRUNCATABLES:
        break

print(truncatables, sum(truncatables))
