import sys
n = int(sys.stdin.readline())
ns = [int(sys.stdin.readline()) for _ in range(n)]
table = {}
for i in ns:
    if table.get(i,-1) == -1:
        table[i] = 0
    else:
        table[i] = table[i]+1
maxV = 0
result = []
for i in table.keys():
    if table[i] > maxV:
        result = [i]
        maxV = table[i]
    elif table[i] == maxV:
        result.append(i)
result.sort()
print(result[0])