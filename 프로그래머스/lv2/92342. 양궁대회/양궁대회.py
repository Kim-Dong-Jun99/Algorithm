from itertools import combinations_with_replacement
from collections import defaultdict


def compare(ryan, apeach, win_dict): 
    ryan_score = 0
    apeach_score = 0

    for i in range(11): 
        if apeach[i] >= ryan[i]:
            if apeach[i] == 0:
                continue
            apeach_score += 10 - i
        else:
            ryan_score += 10 - i

    if ryan_score > apeach_score:  
        win_dict[ryan_score - apeach_score].append(ryan)
    return win_dict


def solution(n, info):
    cases = list(combinations_with_replacement([i for i in range(11)], n))
    cands = []
    win_dict = defaultdict(list)

    for case in cases:  
        ryan = [0] * 11
        for i in case:
            ryan[i] += 1
        cands.append(ryan)

    for cand in cands:
        win_dict = compare(cand, info, win_dict) 

    if not win_dict: 
        return [-1]

    target = win_dict[max(win_dict.keys())]  
    target.sort(key=lambda x: tuple([-x[i] for i in range(10, -1, -1)]))  
    return target[0]