import sys
from collections import defaultdict
a,b = map(int, sys.stdin.readline().split())

next = [a]
visited = defaultdict(int)
index = 1
while next:
    temp = []
    index += 1
    done = False
    for i in next:
        if visited[i] == 0:
            if b >= i * 10 + 1:
                temp.append(i*10+1)
            if b >= i * 2:
                temp.append(i*2)
            if i*10+1 == b or i*2 == b:
                done=True
                print(index)
                sys.exit()
            visited[i] = 1
    next = temp

print(-1)