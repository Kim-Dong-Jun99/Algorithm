import sys
from itertools import combinations
tiny = [int(sys.stdin.readline()) for i in range(9)]
for i in combinations(tiny,7):
    if sum(i) == 100:
        temp = list(i)
        for j in sorted(temp):
            print(j)
        break

