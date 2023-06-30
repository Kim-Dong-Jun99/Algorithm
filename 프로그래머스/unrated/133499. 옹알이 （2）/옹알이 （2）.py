def solution(babbling):
    answer = 0
    
    can_pronounce = ["aya", "ye", "woo", "ma"]
    for word in babbling:
        previous = -1
        
        while len(word) > 0:
            done = True
            for i in range(4):
                if word.find(can_pronounce[i]) == 0:
                    if i != previous:
                        done = False
                        word = word[len(can_pronounce[i]):]
                        previous = i
                    else:
                        break
            if done:
                break
        if len(word) == 0:
            answer += 1
        
        
    return answer