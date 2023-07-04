from collections import defaultdict
def solution(survey, choices):
    answer = ''
    """
    R - T
    C - F
    J - M
    A - N
    survery에서 먼저 나오면 비동의일때 점수를 얻음,
    매우 - 3
    보통 - 2
    약간 - 1
    모르겠음 - 0
    4가 기준,
    점수가 같으면 사전순으로 빠른친구를 리턴
    """
    scores = defaultdict(lambda : 0)
    for i in range(len(choices)):
        disagree, agree = survey[i][0], survey[i][1]
        # print(disagree, agree)
        if choices[i] > 4:
            scores[agree] += choices[i] - 4
        else:
            scores[disagree] += 4 - choices[i]
    if scores["R"] < scores["T"]:
        answer += "T"
    else:
        answer += "R"
    if scores["C"] < scores["F"]:
        answer += "F"
    else:
        answer += "C"
    if scores["J"] < scores["M"]:
        answer += "M"
    else:
        answer += "J"
    if scores["A"] < scores["N"]:
        answer += "N"
    else:
        answer += "A"
        
    return answer