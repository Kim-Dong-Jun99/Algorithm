import sys

n = int(sys.stdin.readline())
buildings = [int(sys.stdin.readline()) for _ in range(n)]
stack = []
result = 0
for i in range(n):
    if i == 0:
        stack.append(buildings[i])
    else:
        if buildings[i] < stack[-1]:
            result += len(stack)
            stack.append(buildings[i])
        else:
            while stack and buildings[i] >= stack[-1]:
                stack.pop()
            result += len(stack)
            stack.append(buildings[i])


print(result)

