from collections import defaultdict
def solution(msg):
    answer = []
    index_word = [0]
    indexed = defaultdict(lambda : 0)
    for i in range(65, 91):
        index_word.append(chr(i))
        indexed[chr(i)] = i - 64
    index = 0
    while index < len(msg):
        temp = msg[index]
        value = indexed[temp]
        while temp in indexed:
            index += 1
            value = indexed[temp]
            if index == len(msg):
                break
            
            temp += msg[index]
        answer.append(value)
        index_word.append(temp)
        indexed[temp] = len(index_word) - 1
            
    return answer