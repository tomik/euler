
import math
import elib

def gen_digit_ranges():
    """
    Generates tuples (num_digits, first_element inclusive, last_element exclusive)
    """
    last = (1, 1, 10)
    while 1:
        yield last
        last = (last[0] + 1, last[1] * 10, last[2] * 10)

def get_digit(pos):
    """
    gets digit on position pos in concatenated fraction 12345678910111213141516.

    >>> get_digit(5)
    5

    >>> get_digit(12)
    1

    >>> get_digit(22)
    6
    """
    for digits, begin, end in gen_digit_ranges():
        digits_in_range = digits * (end - begin)
        if pos <= digits_in_range:
            num = begin - 1 + int(math.ceil(pos / float(digits)))
            return elib.to_digits(num)[pos % digits - 1]
        pos -= digits_in_range


print (reduce(lambda a, b: a * b, map(get_digit, [10 ** pow for pow in range(0, 7)])))
