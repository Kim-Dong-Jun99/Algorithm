from collections import defaultdict
def solution(X, Y):
    answer = []
    X_count = defaultdict(lambda : 0)
    Y_count = defaultdict(lambda : 0)
    for i in X:
        X_count[i] += 1
    for i in Y:
        Y_count[i] += 1
    for i in range(9,-1,-1):
        # answer.append(str(i) * min(X_count[str(i)], Y_count[str(i)]))
        answer.append(''.join([str(i) for _ in range(min(X_count[str(i)], Y_count[str(i)]))]))
    # print(answer)
    result = ''.join(answer)
    # print(result)
    if result == '':
        return "-1"
    if result[0] == '0':
        return '0'
    return result