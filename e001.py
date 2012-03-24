
def three_five_seed(l):
    three_multis = filter(lambda x: x % 3 == 0, l)
    five_multis = filter(lambda x: x % 5 == 0, l)
    return list(set(three_multis + five_multis))


print sum(three_five_seed(xrange(1000)))

