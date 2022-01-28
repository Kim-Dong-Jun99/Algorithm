import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))

isprime = [0 for i in range(1000+1)]
isprime[1] = 1
for i in range(2,1001):
    if isprime[i] == 0:
        for j in range(2,1000//i+1):
            isprime[j*i] = 1
result = 0
for i in ns:
    if isprime[i] == 0:
        result += 1
print(result)