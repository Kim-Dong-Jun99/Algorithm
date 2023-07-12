from collections import deque
def solution(s):
    answer = 0
    n = len(s)
    key = {'}':'{', ']':'[', ')':'('}
    for i in range(n):
        stack = deque()
        done = True
        for j in range(n):
            index = i + j
            if index >= n:
                index -= n
            if s[index] == '[' or s[index] == '(' or s[index] == '{':
                stack.append(s[index])
            else:
                if len(stack) > 0 and key[s[index]] == stack[-1]:
                    stack.pop()
                else:
                    done = False
                    break
        if done and len(stack) == 0:
            answer += 1
    return answer