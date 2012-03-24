
import elib
import itertools

for i in range(9, 1, -1):
    for perm in itertools.permutations(range(i, 0, -1)):
        n = elib.from_digits(perm)
        if elib.is_prime(n):
            print(n)
            return

