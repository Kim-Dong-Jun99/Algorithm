import sys
n = int(sys.stdin.readline())
query = [list(sys.stdin.readline().split()) for i in range(n)]
deque = []
for i in query:
    if i[0] == 'push_front':
        deque.insert(0,int(i[1]))
    elif i[0] == 'push_back':
        deque.append(int(i[1]))
    elif i[0] == 'pop_front':
        if deque:
            print(deque.pop(0))
        else:
            print(-1)
    elif i[0] == 'pop_back':
        if deque:
            print(deque.pop())
        else:
            print(-1)
    elif i[0] == 'size':
        print(len(deque))
    elif i[0] == 'empty':
        if deque:
            print(0)
        else:
            print(1)
    elif i[0] == 'front':
        if deque:
            print(deque[0])
        else:
            print(-1)
    elif i[0] == 'back':
        if deque:
            print(deque[-1])
        else:
            print(-1)