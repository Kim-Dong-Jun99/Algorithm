from bisect import bisect_left
def solution(sequence, k):
    """
    21:06
    """
    answer = [0, len(sequence)-1]
    subsums = [sequence[0]]
    for i in range(1, len(sequence)):
        subsums.append(subsums[-1] + sequence[i])
    for i in range(len(sequence)):
        
        if i == 0:
            lookFor = k
            find = bisect_left(subsums, lookFor)
            if find == len(sequence):
                continue
            # print(find)
            if subsums[find] == k:
                if find < len(sequence)-1:
                    answer = [0, find]
        else:
            lookFor = k + subsums[i-1]
            find = bisect_left(subsums, lookFor)
            if find == len(sequence):
                continue
            
            # print(find)
            if subsums[find] == k+subsums[i-1]:
                if find - i < answer[1] - answer[0]:
                    answer[0] = i
                    answer[1] = find
        
    return answer