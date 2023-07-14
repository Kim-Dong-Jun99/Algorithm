from math import ceil
from collections import deque
def solution(progresses, speeds):
    answer = []
    stack = deque()
    for i in range(len(progresses)):
        if len(stack) == 0:
            stack.append(ceil((100 - progresses[i]) / speeds[i] ))
        else:
            temp = ceil((100 - progresses[i]) / speeds[i] )
            if temp <= stack[-1]:
                stack.appendleft(temp)
            else:
                answer.append(len(stack))
                stack = deque([temp])
    if len(stack) > 0:
        answer.append(len(stack))
                
    return answer