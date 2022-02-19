import sys

table = {}
n = int(sys.stdin.readline())
for _ in range(n):
    q = sys.stdin.readline().strip()
    if table.get(q, -1) == -1:
        table[q] = 1
    else:
        table[q] = table[q] + 1
result = []
maxV = 0
for i in table.keys():
    if table[i] > maxV:
        maxV = table[i]
        result = [i]
    elif table[i] == maxV:
        result.append(i)

result.sort()
print(result[0])