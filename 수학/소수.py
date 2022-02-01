import sys

m = int(sys.stdin.readline())
n = int(sys.stdin.readline())
primes = []
for i in range(m,n+1):
    check = True
    for j in range(2,int(i**(0.5))+1):
        if i%j == 0:
            check = False
            break
    if check and i != 1:
        primes.append(i)
if primes:
    print(sum(primes))
    print(primes[0])
else:
    print(-1)