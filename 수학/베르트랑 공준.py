import sys
def isPrime(n):
    if n == 1:
        return False
    for i in range(2,int(n**0.5)+1):
        if n%i == 0:
            return False
    return True
ns = list(range(2,246912))
ps = []

for i in ns:
    if isPrime(i):
        ps.append(i)
while True:
    n = int(sys.stdin.readline())
    if n == 0:
        break
    else:
        count = 0
        for i in ps:
            if n < i <= 2*n:
                count += 1
        print(count)