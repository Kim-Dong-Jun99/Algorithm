from itertools import combinations
import sys
while True:
    query = list(map(int,sys.stdin.readline().split()))
    if query[0] != 0:
        cand = query[1:]
        cand.sort()
        for i in combinations(cand,6):
            for j in i:
                print(j,end = ' ')
            print()
        print()
    else:
        break