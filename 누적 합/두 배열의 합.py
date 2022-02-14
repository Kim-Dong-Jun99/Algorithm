import sys

t = int(sys.stdin.readline())
n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
m = int(sys.stdin.readline())
ms = list(map(int, sys.stdin.readline().split()))

sumn = [0] * n
sumn[0] = ns[0]
tablen = {}
tablem = {}
for i in range(1, n):
    sumn[i] += ns[i]
    sumn[i] += sumn[i - 1]

summ = [0] * m
summ[0] = ms[0]
for i in range(1, m):
    summ[i] += ms[i]
    summ[i] += summ[i - 1]

for i in sumn:
    if tablen.get(i, -1) == -1:
        tablen[i] = 1
    else:
        tablen[i] += 1

for i in summ:
    if tablem.get(i, -1) == -1:
        tablem[i] = 1
    else:
        tablem[i] += 1

for i in range(n):
    for j in range(i + 1, n):
        if tablen.get(sumn[j] - sumn[i], -1) == -1:
            tablen[sumn[j] - sumn[i]] = 1
        else:
            tablen[sumn[j] - sumn[i]] += 1

for i in range(m):
    for j in range(i + 1, m):
        if tablem.get(summ[j] - summ[i], -1) == -1:
            tablem[summ[j] - summ[i]] = 1
        else:
            tablem[summ[j] - summ[i]] += 1

result = 0
for i in tablen.keys():
    if tablem.get(t - i, -1) != -1:
        result += tablem[t - i] * tablen[i]
print(result)