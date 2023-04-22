from collections import defaultdict
def solution(k, tangerine):
    answer = 0
    same_size = defaultdict(int)
    for_sort = []
    for i in tangerine:
        if same_size[i] == 0:
            for_sort.append(i)
        same_size[i] += 1
    for_sort.sort(reverse=True, key=lambda x : same_size[x])
    # print(for_sort)
    for i in for_sort:
        answer += 1
        k -= same_size[i]
        if k <= 0:
            break
    return answer