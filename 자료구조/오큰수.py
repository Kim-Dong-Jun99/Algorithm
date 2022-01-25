import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
stack = []
NGE = [0 for i in range(n)]
for i in range(n):
    while stack:
        if ns[stack[-1]] < ns[i]:
            NGE[stack[-1]] = ns[i]
            stack.pop()
        else:
            break
    stack.append(i)
for i in range(n):
    if NGE[i] == 0:
        print('-1',end = ' ')
    else:
        print(NGE[i],end = ' ')