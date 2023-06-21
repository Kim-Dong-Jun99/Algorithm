from collections import deque
def solution(ingredient):
    answer = 0
    stack = deque([])
    for i in ingredient:
        stack.append(i)
        if len(stack) >= 4:
            if stack[-4] == 1 and stack[-3] == 2 and stack[-2] == 3 and stack[-1] == 1:
                stack.pop()
                stack.pop()
                stack.pop()
                stack.pop()
                answer += 1
            
    return answer