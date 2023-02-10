import math
import sys

def is_prime(m):
    right = int(math.sqrt(m))
    for i in range(2,right+1):
        if m % i == 0:
            return False

    return True


while True:
    n = int(sys.stdin.readline())
    result = True
    if n == 0:
        break
    for a in range(3, int(n/2)+1):
        if a % 2 == 1:
            if is_prime(a):
                if is_prime(n-a):
                    sys.stdout.write("%d = %d + %d"%(n,a,n-a))
                    sys.stdout.write("\n")
                    result = False
                    break

    if result:
        sys.stdout.write("Goldbach's conjecture is wrong.\n")
