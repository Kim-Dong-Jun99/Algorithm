import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
ns.sort()
result = 1
for i in ns:
    if result < i:
        break
    result += i
print(result)