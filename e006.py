"""
The sum of the squares of the first ten natural numbers is,
1^(2) + 2^(2) + ... + 10^(2) = 385

The square of the sum of the first ten natural numbers is,
(1 + 2 + ... + 10)^(2) = 55^(2) = 3025

Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 - 385 = 2640.

Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
"""

def sum_squares(numbers):
    return sum(map(lambda x: x**2, numbers))

def squares_sum(numbers):
    return sum(numbers)**2

def ss_diff(numbers):
    return abs(sum_squares(numbers) - squares_sum(numbers))

if __name__ == '__main__':
    print ss_diff(xrange(1, 101))

