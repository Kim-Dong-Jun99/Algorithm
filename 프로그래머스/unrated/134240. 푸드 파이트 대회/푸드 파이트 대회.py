def solution(food):
    left = ''
    right = ''
    for i in range(1, len(food)):
        plus = food[i] // 2
        left += str(i)*plus
        right = str(i)*plus + right
    return left + '0' + right