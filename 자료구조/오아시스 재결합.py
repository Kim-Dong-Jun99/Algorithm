import sys
n = int(sys.stdin.readline())
result = 0
stack = []
for i in range(n):
    stack.append(int(sys.stdin.readline()))
    if i >= 1:
        cur = stack[-1]
        tallest = stack[-2]
        for j in range(len(stack)-2, -1, -1):
            if stack[j] >= tallest:
                result += 1
                tallest = stack[j]
            # else:
            #     break

print(result)