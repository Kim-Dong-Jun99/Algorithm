from collections import defaultdict
def solution(scores):
    wanho = scores[0]
    scores.sort(reverse = True, key = lambda x : x[0]*100001 - x[1])
    answer = 0
    rank = defaultdict(int)
    cur_max = scores[0][1]
    rank[sum(scores[0])] += 1
    wanho_insentive = False
    if scores[0] == wanho:
        wanho_insentive = True
    for i in range(1, len(scores)):
        if cur_max <= scores[i][1]:
            rank[sum(scores[i])] += 1
            cur_max = scores[i][1]
            if scores[i] == wanho:
                wanho_insentive = True
    if wanho_insentive == False:
        return -1
    sum_wanho = sum(wanho)
    for i in rank:
        if i > sum_wanho:
            answer += rank[i]
    return answer + 1