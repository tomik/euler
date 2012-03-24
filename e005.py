"""
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest number that is evenly divisible by all of the numbers from 1 to 20?
"""

import elib

def smallest_divisible_by(dividers):
    max_divider = max(list(dividers))
    primes = elib.primes_to(max_divider)
    #min to start with
    min = reduce(lambda x, y: x* y, primes, 1)
    act = min
    while True:
        for d in dividers:
            if act % d != 0:
                act += min
                break
        else:
            return act

if __name__ == '__main__':
    
    print smallest_divisible_by(xrange(1, 21))
