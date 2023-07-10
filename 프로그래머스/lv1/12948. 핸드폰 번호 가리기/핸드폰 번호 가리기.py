def solution(phone_number):
    answer = ''
    index = 0
    while index + 4 < len(phone_number):
        index += 1
        answer += '*'
    while index < len(phone_number):
        answer += phone_number[index]
        index += 1
    return answer