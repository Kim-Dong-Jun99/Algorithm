import sys
input = sys.stdin.readline
N, K = map(int, input().split())
use = list(map(int, input().split()))

plugs = []
result = 0
for i in range(K):
    if use[i] in plugs:
        continue

    if len(plugs) != N:
        plugs.append(use[i])
        continue

    far_one = 0
    temp = 0
    for plug in plugs:
        if plug not in use[i:]:
            temp = plug
            break
        elif use[i:].index(plug) > far_one:
            far_one = use[i:].index(plug)
            temp = plug
    plugs[plugs.index(temp)] = use[i]
    result += 1

print(result)