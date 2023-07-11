def solution(s):
    answer = [0,0]
    while s != "1":
        temp = ""
        for i in s:
            if i == "1":
                temp += i
            else:
                answer[1] += 1
        answer[0] += 1
        s = bin(len(temp))[2:]
        
    return answer