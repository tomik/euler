
a_limit, b_limit = (101, 101)
s = set((a**b for a in xrange(2, a_limit) for b in xrange(2, b_limit)))
print(len(s))
