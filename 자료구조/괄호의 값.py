n = list(input())

stack = []
result = 0
temp = 1
for i in range(len(n)):
    if n[i] == '(':
        stack.append(n[i])
        temp *= 2
    elif n[i] == '[':
        stack.append(n[i])
        temp *= 3
    elif n[i] == ')':
        if not stack or stack[-1] == '[':
            result = 0
            break
        if n[i - 1] == '(':
            result += temp
        stack.pop()
        temp //= 2
    else:
        if not stack or stack[-1] == '(':
            result = 0
            break
        if n[i - 1] == '[':
            result += temp
        stack.pop()
        temp //= 3
if stack:
    print(0)
else:
    print(result)
