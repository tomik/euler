"""
Library for solving euler project problems.
"""

import itertools
import math


def fib():
    """
    Fibonacci number generator
    """
    p = 1
    pp = 0
    yield 1 
    while True:
        yield p + pp 
        p, pp = p + pp, p

def digit_rotator(num):
    """
    rotates number by digits
    
    >>> list(digit_rotator(1))
    [1]

    >>> list(digit_rotator(21))
    [12, 21]
    """
    s = str(num)
    for i in range(len(s)):
        s = s[-1] + s[:-1]
        yield int(s)

def filtered_generator(it, filter):
    """
    Wrapper for filtering generator.
    """
    while True:
        f = it.next()
        if filter(f):
            yield f

def conditioned_generator(it, func):
    """
    Wrapper for conditioning generator.
    """
    while True:
        item = it.next()
        if not func(item):
            raise StopIteration
        yield item


def perm_gen(l):
    """
    Generator for permutation of list l.
    """
    if len(l) == 1:
        yield l
        raise StopIteration 
        
    for p in perm_gen(l[1:]):
        for i in range(len(p)):
            yield p[:i] + [l[0]] + p[i:]
        yield p + [l[0]] 
    raise StopIteration 


def is_prime(number):
    """
    >>> is_prime(2) 
    True

    >>> is_prime(10)
    False

    >>> is_prime(7)
    True

    >>> is_prime(9)
    False

    >>> is_prime(35)
    False

    >>> is_prime(199)
    True
    """
    if number <= 0:
        return False

    if number <= 7:
        return {0: False, 1: False, 2: True, 3: True, 4: False, 5: True, 6: False, 7:True}[number]
    
    if number % 2 == 0 or number % 3 == 0: 
        return False
    
    max = int(math.sqrt(number))
    i = 6
    while True:
        if number % (i - 1) == 0 or number % (i + 1) == 0:
            return False
        if i > max:
            break
        i += 6

    return True

def primes(start=2):
    """
    Primes generator.
    """

    i = start 
    while True:
        if is_prime(i):
            yield i
        i += 1

def nth_prime(n):
    """
    >>> nth_prime(1)
    2

    >>> nth_prime(6) 
    13

    >>> nth_prime(13)
    41
    """
    return next(itertools.islice(primes(), n - 1, None))

def primes_to(max):
    """
    Primes up to certain limit. 

    simple
    >>> primes_to(2)
    [2]

    >>> primes_to(10)
    [2, 3, 5, 7]

    >>> primes_to(20)
    [2, 3, 5, 7, 11, 13, 17, 19]
    """
    def dosieve(sieve, max): 
        for i in range(2, int(math.sqrt(max))+1): 
            if not sieve[i]: 
                continue 
            for j in range(i*i, max+1, i): 
                sieve[j] = None 

    sieve = range(max+1) 
    dosieve(sieve, max)
    return filter(None, sieve[2:])

def composite(number, start_from):
    top = int(math.sqrt(number))
    for i in xrange(start_from, top + 1):
        if number % i == 0:
            return [i] + composite(number/i, i) 

    return [number]

def prime_factors(number):
    return list(set(composite(number, 2)))

def divisors(number):
    """
    >>> divisors(1)
    [1]

    >>> divisors(3)
    [1, 3]

    >>> divisors(10)
    [1, 2, 5, 10]

    >>> divisors(28)
    [1, 2, 4, 7, 14, 28]

    >>> divisors(220)
    [1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110, 220]
    """

    ds = [1, number]

    top = int(math.sqrt(number))
    for i in xrange(2, top + 1):
        if number % i == 0:
            ds.append(i) 
            ds.append(number/i)

    return sorted(set(ds))

def is_palindrome(number):
    """
    >>> is_palindrome(10)
    False

    >>> is_palindrome(101)
    True

    >>> is_palindrome(123321)
    True

    >>> is_palindrome(123421)
    False
    """
    l1 = list(str(number))
    l2 = l1[:]
    l2.reverse()
    return l1 == l2

def is_pandigital(n, pandigit_power):
    digits = to_digits(n)
    return len(digits) == pandigit_power and \
           set(digits) == set(range(1, pandigit_power + 1))


def doubles_from_to(bottom, top):
    """
    Generator of doubles from the bottom to the top.
    """
    n1 = bottom 
    n2 = bottom
    while True:
        if n2 == top:
            n1 += 1 
            n2 = n1
            if n1 == top:
                raise StopIteration
        yield (n1, n2)
        n2 += 1

def last_n_power(number, power, n):
    """
    Computes last n digits of number**power.
    """
    m = 10 ** n 
    act = number 
    for i in xrange(1, power):
        act *= number
        act %= m
    
    return act


def load_numbers(fn, split=False):
    numbers = open(fn,'r').read().strip()
    if split:
        numbers = numbers.splitlines()
        ns = []
        for i in xrange(len(numbers)):
            ns += numbers[i].split(' ')
        numbers = ns
    else:
        numbers = list(numbers)
    numbers = filter(len, numbers)
    numbers = map(int, numbers)
    return numbers

def factorial(num):
    assert(num >= 0)
    res = 1
    while num > 0:
        res *= num
        num -= 1
    return res


def horner(l):
    """
    Horner scheme - takes list and returns integer.
    """
    res = 0
    for d in l:
        res = 10 * res + d
    return res

def from_digits(l):
    return horner(l)


def to_digits(num):
    return map(int, list(iter(str(num))))

if __name__ == '__main__':
    import doctest
    doctest.testmod()

