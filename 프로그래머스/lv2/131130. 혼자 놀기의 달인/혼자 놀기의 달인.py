def solution(cards):
    
    groups = []
    visited = [0]*len(cards)
    for i in range(len(cards)):
        if visited[i] == 0:
            cur = i
            size = 0
            while visited[cur] == 0:
                visited[cur] = 1
                size += 1
                cur = cards[cur]-1
            groups.append(size)
    if len(groups) == 1:
        return 0
    groups.sort()
    return groups[-1] * groups[-2]