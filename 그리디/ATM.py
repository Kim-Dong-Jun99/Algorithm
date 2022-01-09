import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
result = 0
sum = 0
ns.sort()
for i in ns:
    sum += i
    result += sum
print(result)