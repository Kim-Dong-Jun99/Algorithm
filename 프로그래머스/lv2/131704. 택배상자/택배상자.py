from collections import deque
def solution(order):
    answer = 0
    stack = deque()
    belt = deque([i for i in range(1,len(order)+1)])
    for i in order:
        while belt and belt[0] <= i:
            stack.append(belt.popleft())
        if stack[-1] == i:
            stack.pop()
            answer += 1
        else:
            break
    
    
            
    return answer