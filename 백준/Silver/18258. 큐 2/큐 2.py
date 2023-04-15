from collections import deque
import sys
q = deque([])
n = int(sys.stdin.readline())
for _ in range(n):
    query = list(sys.stdin.readline().split())
    if query[0] == 'push':
        q.append(query[1])
    elif query[0] == 'pop':
        if q:
            print(q.popleft())
        else:
            print(-1)
    elif query[0] == 'size':
        print(len(q))
    elif query[0] == 'empty':
        if q:
            print(0)
        else:
            print(1)
    elif query[0] == 'front':
        if q:
            print(q[0])
        else:
            print(-1)
    elif query[0] == 'back':
        if q:
            print(q[-1])
        else:
            print(-1)