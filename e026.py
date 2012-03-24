import sys

def find_cycle(denominator):
    """
    >>> find_cycle(5)
    []
    >>> find_cycle(3)
    [10]
    >>> find_cycle(6)
    [40]
    >>> find_cycle(7)
    [10, 30, 20, 60, 40, 50]
    """
    residuals = []
    numerator = 10
    while 1:
        if numerator == 0:
            return []
        new_digit = numerator / denominator
        try:
            index = residuals.index(numerator)
            return residuals [index:]
        except ValueError:
            pass
        residuals.append(numerator)
        numerator = 10 * (numerator % denominator)


l, i = max(((len(find_cycle(i)), i) for i in xrange(1, 1000)))
print (l, i)

