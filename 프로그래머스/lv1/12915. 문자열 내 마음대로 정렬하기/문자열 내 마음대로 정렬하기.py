from collections import defaultdict
def solution(strings, n):
    answer = []
    table = defaultdict(lambda : [])
    for i in strings:
        table[i[n]].append(i)
    keys = sorted(table.keys())
    for k in keys:
        temp = table[k]
        temp.sort()
        for i in temp:
            answer.append(i)
    return answer