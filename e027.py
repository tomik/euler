
import elib

def check_coefs(a, b):
    i = 0
    while elib.is_prime(i * i + i * a + b):
        i += 1
    return i

def gen_coefs(min_limit, max_limit):
    return ((a, b) for a in xrange(min_limit, max_limit)
                   for b in elib.primes_to(max_limit))

m = max((check_coefs(a, b), a, b) for a, b in gen_coefs(-1000, 1000))
print(m)

