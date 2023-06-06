import sys
from itertools import combinations
from collections import defaultdict
T=int(sys.stdin.readline())

def measure(a,b,c):
    result = 0
    for i in range(4):
        if a[i] != b[i]:
            result += 1
        if a[i] != c[i]:
            result += 1
        if b[i] != c[i]:
            result += 1
    return result

for ____ in range(T):
    N=int(sys.stdin.readline())
    people=list(sys.stdin.readline().split())
    mbtis = defaultdict(lambda:0)
    for i in people:
        mbtis[i] += 1
    for_comb = []
    for i in mbtis.keys():
        if mbtis[i] > 3:
            for j in range(3):
                for_comb.append(i)
        else:
            for j in range(mbtis[i]):
                for_comb.append(i)
    combi = list(combinations(for_comb, 3))
    result = sys.maxsize
    for a,b,c in combi:
        temp_result = measure(a,b,c)
        if temp_result < result:
            result = temp_result
    sys.stdout.write("%d\n"%result)