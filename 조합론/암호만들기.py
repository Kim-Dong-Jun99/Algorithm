import sys
from itertools import combinations

L, C = map(int, sys.stdin.readline().split())
cand = list(sys.stdin.readline().split())
vowels = []
rest = []
table = ['a', 'e', 'i', 'o', 'u']
for i in cand:
    if (i in table):
        vowels.append(i)
    else:
        rest.append(i)
vowels.sort()
rest.sort()
result = []
for i in range(1, len(vowels) + 1):
    if i <= L:
        for j in combinations(vowels, i):
            if 2 <= L - i:
                for k in combinations(rest, L - i):
                    temp = list(j + k)
                    temp.sort()
                    result.append(temp)
    else:
        break
result.sort()
for i in result:
    for j in i:
        print(j, end='')
    print()
