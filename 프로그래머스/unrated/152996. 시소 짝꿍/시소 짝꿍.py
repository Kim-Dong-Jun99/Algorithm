from bisect import bisect_left
from collections import defaultdict
def solution(weights):
    """12:46"""
    result = {}
    count = defaultdict(int)
    for i in weights:
        count[i] += 1
    weights.sort()
    for i in range(len(weights)):
        weightToFind = [weights[i]*2, weights[i]*3, weights[i]*4]
        dis = [2,3,4]
        for j in weightToFind:
            for k in dis:
                toFind = j / k
                if toFind == int(toFind):
                    index = bisect_left(weights, toFind)
                    if 0 <= index and index < len(weights) and weights[index] * k == j:
                        if weights[index] != weights[i]:
                            result[(smaller(weights[i], weights[index]), bigger(weights[i], weights[index]))] = count[weights[index]] * count[weights[i]]
                        elif weights[index] == weights[i] and count[weights[index]] >= 2:
                            result[(smaller(weights[i], weights[index]), bigger(weights[i], weights[index]))] = count[weights[i]] * (count[weights[i]]-1) / 2
                        
                            
    # print(result)
    return sum(result.values())

def smaller(i, j):
    if i < j:
        return i
    return j

def bigger(i, j):
    if i < j:
        return j
    return i