def solution(elements):
    result = set()
    save = 0
    subsums = []
    for i in elements:
        save += i
        subsums.append(save)
        result.add(i)
    
    for d in range(1, len(elements)-1):
        result.add(subsums[d])
        for i in range(1,len(elements)):
            if i + d < len(elements):
                result.add(subsums[i+d] - subsums[i-1])
            else:
                result.add(subsums[-1] - subsums[i-1] + subsums[i+d - len(elements)])
                
    result.add(subsums[-1])
    return len(result)