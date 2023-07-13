from collections import defaultdict
from itertools import combinations
def solution(clothes):
    answer = 1
    closet = defaultdict(lambda : [])
    for name, kind in clothes:
        closet[kind].append(name)
    for i in closet:
        answer *= (len(closet[i]) + 1)
        
    return answer-1