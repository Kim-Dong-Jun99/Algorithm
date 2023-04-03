import sys
n = int(sys.stdin.readline())
a = list(map(int,sys.stdin.readline().split()))
b = list(map(int,sys.stdin.readline().split()))
a.sort()
b.sort()
result = 0
for i in range(n):
    result += b[n-1-i]*a[i]
print(result)