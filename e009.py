"""
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
a^(2) + b^(2) = c^(2)

For example, 3^(2) + 4^(2) = 9 + 16 = 25 = 5^(2).

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
"""


def is_pyth_triplet(a, b, c):
    return a**2 + b**2 == c**2

def gen_sum_tuples(sum_to, elements_num):
    """
    Generates all the tuples with elements_num elements summing to sum_to.
    In tuple i-th element is always < than i+k-th element - i.e. tuples are unordered.
    """
    def step_back(l, i):
        l[i] = 0
        l[i-1] += 1
        return i - 1
        
    
    l = [0] * elements_num 
    i = 0

    while True:
        #move forward
        i += 1
      
        #last one ... add to sum_to
        if i == elements_num -1 :
            l[i] = sum_to - sum(l)
            yield tuple(l)
            i = step_back(l, i)
        else:
            l[i] = l[i-1] + 1

        #traceback
        while (sum_to - sum(l))/float(elements_num - i - 1) <= l[i]: 
            if i == 0:
                raise StopIteration
            i = step_back(l, i)

if __name__ == '__main__':
    for t in gen_sum_tuples(1000, 3):
        if is_pyth_triplet(t[0], t[1], t[2]):
            print t, reduce(lambda x, y: x * y, t, 1)
            break
