
import collections
import math

MAX_PERIMETER = 1000

pyths = collections.defaultdict(int)

for c in xrange(MAX_PERIMETER, 1, -1): 
    for b in xrange(c - 1, 1, -1):
        a =  math.sqrt(c*c - b*b)
        if a <= b and a + b + c <= MAX_PERIMETER and math.floor(a) == a:
            pyths[int(a) + b + c] += 1

k, v = max(pyths.items(), key=lambda pair: pair[1])
print(k, v)


