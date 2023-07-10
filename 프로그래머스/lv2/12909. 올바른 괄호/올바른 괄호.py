from collections import deque
def solution(s):
    answer = True
    stack = deque()
    for i in s:
        if i == '(':
            stack.append(i)
        else:
            if len(stack) > 0:
                stack.pop()
            else:
                return False
    if len(stack) > 0:
        return False
    return True