from collections import defaultdict
def solution(storey):
    answer = 0
    
    visited = defaultdict(int)
    cur = [storey]
    while cur:
        temp = []
        for c in cur:
            for d in range(10):
                if c % 10**d != 0:
                    interest = c - 10**(d-1)
                    if visited[interest] == 0:
                        visited[interest] = 1
                        temp.append(interest)
                    
                    break
            for d in range(10):
                if c % 10**d != 0:
                    interest = c + 10 ** (d-1)
                    if visited[interest] == 0:
                        visited[interest] = 1
                        temp.append(interest)
                    break
        cur = temp
        answer += 1
        if visited[0] == 1:
            break
                                    
    return answer