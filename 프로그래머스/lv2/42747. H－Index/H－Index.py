def solution(citations):
    answer = 0
    citations.sort()
    limit = max(citations)
    h = 0
    index = 0
    while h <= limit:
        while index < len(citations) and citations[index] < h:
            index += 1
        if len(citations) - index >= h:
            if h > answer:
                answer = h
        h += 1
    return answer