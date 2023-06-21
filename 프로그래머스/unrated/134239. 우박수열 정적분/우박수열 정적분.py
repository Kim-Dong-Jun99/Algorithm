def solution(k, ranges):
    answer = []
    heights = [k]
    while True:
        if k == 1:
            break
        if k % 2 == 0:
            k /= 2
        else:
            k *= 3
            k += 1
        heights.append(k)
    size = []
    end = len(heights)
    for i in range(end-1):
        size.append(sum([heights[i], heights[i+1]])/2)
    subsums = []
    temp_sum = 0
    for i in size:
        temp_sum += i
        subsums.append(temp_sum)
    # print(end)
    # print(heights)
    # print(size)
    # print(subsums)
    for a,b_ in ranges:
        b = end-1 + b_
        if a > b:
            answer.append(-1.0)
        elif a == b:
            answer.append(0.0)
        else:
            if a == 0:
                answer.append(subsums[b-1])
            else:
                answer.append(subsums[b-1] - subsums[a-1])
            # continue
            
        
        
        
    return answer