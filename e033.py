

def is_curious(a, b):
    f = lambda num: map(int, list(iter(str(num))))
    a_digits = f(a)
    b_digits = f(b)
    assert(len(a_digits) == 2 and len(b_digits) == 2)
    return b_digits[1] != 0 and \
        a / float(b) == a_digits[0] / float(b_digits[1]) and \
        a_digits[1] == b_digits[0]

l = [(a, b) for a in xrange(10, 100) for b in xrange(a + 1, 100) if is_curious(a, b)]
print l
