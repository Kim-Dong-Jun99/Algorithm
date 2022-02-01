import sys

tc = int(sys.stdin.readline())


def isPrime(n):
    if n == 1:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True


for _ in range(tc):
    n = int(sys.stdin.readline())

    mid = int(n / 2)
    if isPrime(mid):
        print('%d %d' % (mid, mid))
    else:
        compare = mid - 1
        while compare > 1:
            if isPrime(compare):
                if isPrime(n - compare):
                    print('%d %d' % (compare, n - compare))
                    break
            compare -= 1

