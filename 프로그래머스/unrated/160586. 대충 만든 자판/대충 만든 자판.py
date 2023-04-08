def solution(keymap, targets):
    """
    19:32
    """
    answer = []
    keyTable = {}
    for i in keymap:
        index = 0
        for j in i:
            index += 1
            if j not in keyTable:
                keyTable[j] = index
            else:
                if keyTable[j] > index:
                    keyTable[j] = index
    for i in targets:
        tempSum = 0
        for j in i:
            if j in keyTable:
                tempSum += keyTable[j]
            else:
                tempSum = -1
                break
        answer.append(tempSum)
            
    
    return answer