
import elib
import itertools

facts = dict([(i, elib.factorial(i)) for i in range(0, 10)])

def is_curious(num):
    """
    >>> is_curious(145)
    True
    >>> is_curious(27)
    False
    """
    digits = elib.to_digits(num)
    return num == sum(map(lambda d: facts[d], digits))

def gen_curious():
    i = 10
    while 1:
        if is_curious(i):
            yield i
        i += 1

print(list(itertools.islice(gen_curious(), 3)))

