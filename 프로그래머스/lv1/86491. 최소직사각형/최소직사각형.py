def solution(sizes):
    answer_max = 0
    answer_min = 0
    for i, j in sizes:
        if max(i,j) > answer_max:
            answer_max = max(i,j)
        if min(i,j) > answer_min:
            answer_min = min(i,j)
    return answer_max * answer_min