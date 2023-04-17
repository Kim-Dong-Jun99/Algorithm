import math
def solution(r1, r2):
    answer = 0
    answer += 4
    for i in range(1, r2):
        if i <= r1:
            y = int((r2**2 - i**2)**0.5)
            y_ = math.ceil((r1**2 - i**2)**0.5)
            # print(y, y_)
            answer += (y-y_+1)*4
        else:
            y = int((r2**2 - i**2)**0.5)
            answer += y * 4
            answer += 4
        
    return answer