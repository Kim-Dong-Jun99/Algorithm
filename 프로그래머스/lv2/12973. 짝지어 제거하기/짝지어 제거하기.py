from collections import deque
def solution(s):
    stack = deque()
    for i in s:
        if len(stack) == 0:
            stack.append(i)
        else:
            if stack[-1] == i:
                stack.pop()
            else:
                stack.append(i)
    if len(stack) == 0:
        return 1
    return 0