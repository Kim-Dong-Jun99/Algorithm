from collections import defaultdict
def solution(x, y, n):
    """12:40"""
    answer = 0
    visited = defaultdict(int)
    cur = [x]
    visited[x] = 1
    isDone = False
    while cur:
        if visited[y] == 1:
            isDone = True
            break
        temp = []
        for i in cur:
            for j in can_go(i, n):
                if visited[j] == 0:
                    temp.append(j)
                    visited[j] = 1
        answer += 1
        cur = temp
        
    if isDone:
        return answer
    else:
        return -1

def can_go(x, n):
    result = []
    if x + n <= 1000000:
        result.append(x + n)
    if x * 2 <= 1000000:
        result.append(x*2)
    if x * 3 <= 1000000:
        result.append(x*3)
    return result