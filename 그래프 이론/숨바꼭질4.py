import sys
from collections import defaultdict


def can_go(i):
    temp = []
    if i < K:
        if i - 1 >= 0 and visited[i - 1] == 0:
            temp.append(i - 1)
        if i + 1 <= K and visited[i + 1] == 0:
            temp.append(i + 1)
        if i * 2 <= 100000 and visited[i * 2] == 0:
            temp.append(i * 2)
        return temp
    else:
        if i - 1 >= 0 and visited[i - 1] == 0:
            temp.append(i - 1)
        return temp


N, K = map(int, input().split())
visited = defaultdict(int)
to_print = defaultdict(int)
visited[N] = 1
go = [N]
result = 0
if N == K:
    print(0)
    print(N)
    sys.exit()
while go:
    temp = []
    for i in go:
        for j in can_go(i):
            visited[j] = 1
            to_print[j] = i
            temp.append(j)
    go = temp
    result += 1
    if visited[K] == 1:
        break
print(result)
result_list = []
p = K
while p != 0:
    result_list.append(p)
    p = to_print[p]
for i in range(len(result_list) - 1, -1, -1):
    sys.stdout.write("%d " % result_list[i])