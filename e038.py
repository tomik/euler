
import elib
import sys

DIGITS = set(range(1, 10))

pan_checks = 0
bests = 0

def is_pandigital(num):
    global pan_checks
    pan_checks += 1
    digits = elib.to_digits(num)
    return len(digits) == 9 and set(digits) == DIGITS

def check_number(num, multiplier, best):
    candidate = int("".join(map(lambda e: str(num * e), multipliers)))
    if candidate > best and is_pandigital(candidate):
        global bests
        bests += 1
        return candidate

def get_limits(n):
    return (1, 10000)

best = {"num": 0, "multipliers": [], "pan": 0}
for n in xrange(2, 10):
    multipliers = range(1, n + 1)
    lower, upper = get_limits(n)
    for num in xrange(lower, upper):
        pan = check_number(num, multipliers, best["pan"])
        if pan:
            best = {"num": num, "multipliers": multipliers, "pan": pan}

print(best)
print(pan_checks, bests)



