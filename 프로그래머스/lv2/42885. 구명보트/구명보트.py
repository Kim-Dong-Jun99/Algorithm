def solution(people, limit):
    answer = 0
    people.sort(reverse = True)
    index = 0
    end_index = len(people) - 1
    while index <= end_index:
        if people[index] + people[end_index] > limit:
            index += 1
            answer += 1
        else:
            answer += 1
            index += 1
            end_index -= 1
    
    return answer