import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
inc = [1 for _ in range(n)]
dec = [1 for _ in range(n)]

for i in range(1,n):
    for j in range(i-1,-1,-1):
        if ns[j] < ns[i]:
            if inc[j] + 1 > inc[i]:
                inc[i] = inc[j]+1
        if ns[-(j+1)] < ns[-(i+1)]:
            if dec[-(j+1)] + 1 > dec[-(i+1)]:
                dec[-(i+1)] = dec[-(j+1)] + 1
result = [0 for _ in range(n)]
for i in range(n):
    result[i] += dec[i]+inc[i]-1
print(max(result))