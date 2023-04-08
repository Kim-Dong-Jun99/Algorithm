def solution(players, callings):
    """
    20:35
    """
    answer = players.copy()
    rank = {}
    for i in range(len(players)):
        rank[players[i]] = i
    for i in callings:
        index = rank[i]
        rank[i] -= 1
        rank[answer[index-1]] += 1
        answer[index], answer[index-1] = answer[index-1], answer[index]
    return answer