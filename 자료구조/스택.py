import sys
n = int(sys.stdin.readline())
stack = []
for i in range(n):
    query = sys.stdin.readline().split()
    temp = query[0]
    if temp == 'push':
        stack.append(query[1])
    elif temp == 'pop':
        if len(stack) >= 1:
            print(stack.pop())
        else:
            print(-1)
    elif temp == 'empty':
        if stack == []:
            print(1)
        else:
            print(0)
    elif temp == 'top':
        if stack == []:
            print(-1)
        else:
            print(stack[-1])
    elif temp == 'size':
        print(len(stack))