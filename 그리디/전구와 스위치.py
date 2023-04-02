import sys
from collections import defaultdict

N = int(sys.stdin.readline())

given = sys.stdin.readline().rstrip()
final = sys.stdin.readline().rstrip()
diff = defaultdict(int)
for i in range(N):
    if given[i] != final[i]:
        diff[i] = 1
result = 0
for i in range(N):
    if diff[i] == 1 and diff[i + 1] == 1 and diff[i + 2] == 1:
        result += 1

print(result)
