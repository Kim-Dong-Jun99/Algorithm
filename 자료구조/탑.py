import sys
n = int(sys.stdin.readline())
ns = list(map(int,sys.stdin.readline().split()))
towers = [0 for i in range(n)]

stack = []
for i in range(n-1,-1,-1):
    if stack:
        while stack:
            if ns[stack[-1]] <= ns[i]:
                towers[stack[-1]] = i+1
                stack.pop()
            else:
                break
    stack.append(i)
for i in towers:
    print(i, end = ' ')