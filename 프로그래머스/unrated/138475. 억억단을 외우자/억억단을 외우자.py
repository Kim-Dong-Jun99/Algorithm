
def solution(e, starts):
    result = []
    div = [1 for _ in range(e+1)]
    remain = 0
    starts_dict = {}
    sorted_starts = sorted(starts)

    for i in range(2, e+1):
        for j in range(i, e+1, i):
            div[j] += 1

    for i in range(len(sorted_starts)):
        if remain == 0:
            max_index = div[sorted_starts[i]:].index(max(div[sorted_starts[i]:]))+sorted_starts[i]
            starts_dict[sorted_starts[i]] = max_index
            remain = max_index
        else:
            if sorted_starts[i] <= remain:
                starts_dict[sorted_starts[i]] = remain
            else:
                remain = div[sorted_starts[i]:].index(max(div[sorted_starts[i]:]))+sorted_starts[i]
                starts_dict[sorted_starts[i]] = remain
    
    for st in starts:
        result.append(starts_dict.get(st))
        
    return result