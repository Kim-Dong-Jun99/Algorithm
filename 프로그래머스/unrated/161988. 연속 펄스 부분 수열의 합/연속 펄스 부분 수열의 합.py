def solution(sequence):
    """
    15:39
    펄스 수열 = 1,-1,1,-1,...
    """

    plus = [sequence[0]]
    # minus = [-sequence[0]]
    for i in range(1,len(sequence)):
        if i % 2 == 1:
            plus.append(plus[-1] - sequence[i])
            # minus.append(minus[-1] + sequence[i])
        else:
            plus.append(plus[-1] + sequence[i])
            # minus.append(minus[-1] - sequence[i])
    if max(plus) * min(plus) > 0:
        return max([abs(max(plus)), abs(min(plus)), abs(max(sequence)), abs(min(sequence))])
    else:
        return max([abs(max(plus) - min(plus)), abs(max(sequence)), abs(min(sequence))])
        