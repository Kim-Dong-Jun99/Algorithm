import sys
n = int(sys.stdin.readline())
query = [sys.stdin.readline().strip() for i in range(n)]
queue = []
for i in query:
    temp = list(i.split())
    if temp[0] == 'push':
        queue.append(temp[1])
    elif temp[0] == 'pop':
        if queue:
            print(queue.pop(0))
        else:
            print(-1)
    elif temp[0] == 'size':
        print(len(queue))
    elif temp[0] == 'empty':
        if queue:
            print(0)
        else:
            print(1)
    elif temp[0] == 'front':
        if queue:
            print(queue[0])
        else:
            print(-1)
    elif temp[0] == 'back':
        if queue:
            print(queue[-1])
        else:
            print(-1)