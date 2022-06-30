import sys
n, m = map(int, sys.stdin.readline().split())
result = 0
map = set()
for i in range(n):
    map.add(sys.stdin.readline())

for i in range(m):
    temp = sys.stdin.readline()
    if (temp in map):
        result += 1

print(result)
