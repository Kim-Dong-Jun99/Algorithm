import sys
from collections import defaultdict
sys.setrecursionlimit(10**7)
n = int(sys.stdin.readline())
tree = defaultdict(lambda : [])
for _ in range(n-1):
    parent, child, value = map(int, sys.stdin.readline().split())
    tree[parent].append([child, value])
    
result = 0

def recur(n):
    if len(tree[n]) == 0:
        return 0
    temp_max = []
    for child, value in tree[n]:
        temp_value = recur(child)
        temp_value += value
        temp_max.append(temp_value)
    global result
    temp_max.sort()
    if len(temp_max) >= 2:
        if result < temp_max[-1] + temp_max[-2]:
            result = temp_max[-1]+temp_max[-2]
    if result < temp_max[-1]:
        result = temp_max[-1]
    return temp_max[-1]
    
recur(1)

print(result)