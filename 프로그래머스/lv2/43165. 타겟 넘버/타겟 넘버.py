from collections import defaultdict
def solution(numbers, target):
    cur = defaultdict(lambda : 0)
    cur[0] = 1
    for i in numbers:
        temp = defaultdict(lambda : 0)
        for j in cur:
            temp[j + i] += cur[j]
            temp[j - i] += cur[j]
        cur = temp
    
    
    return cur[target]