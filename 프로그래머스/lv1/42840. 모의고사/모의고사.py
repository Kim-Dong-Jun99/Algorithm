def solution(answers):
    answer = []
    pattern1 = [1,2,3,4,5]
    pattern2 = [2,1,2,3,2,4,2,5]
    pattern3 = [3,3,1,1,2,2,4,4,5,5]
    correct = [0,0,0]
    for i in range(len(answers)):
        if answers[i] == pattern1[i % 5]:
            correct[0] += 1
        if answers[i] == pattern2[i % 8]:
            correct[1] += 1
        if answers[i] == pattern3[i % 10]:
            correct[2] += 1
    highest = max(correct)
    for i in range(3):
        if correct[i] == highest:
            answer.append(i+1)
    return answer