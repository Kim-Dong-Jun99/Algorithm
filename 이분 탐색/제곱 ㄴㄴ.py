import sys
import math

mul = [0] * 1000010


def setup():
    mul[1] = 1
    for i in range(1, 1000000 + 1):
        for j in range(2 * i, 1000000 + 1, i):
            mul[j] -= mul[i]


def sq_no_no_count(n):
    count = 0
    for i in range(1, int(math.sqrt(n)) + 1):
        count += mul[i] * (n // (i * i))
    return count


K = int(sys.stdin.readline())

setup()
# print("setup done")
left = 1
right = 10 ** 11
while True:
    middle = (left + right) // 2

    temp_v = sq_no_no_count(middle)
    if temp_v == K and left == right:
        print(middle)
        sys.exit()

    if temp_v >= K:
        right = middle
    else:
        left = middle + 1
