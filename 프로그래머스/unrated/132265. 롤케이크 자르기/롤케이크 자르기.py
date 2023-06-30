def solution(topping):
    answer = 0
    from_left = [0]*len(topping)
    from_right = [0]*len(topping)
    left_dict = set()
    right_dict = set()
    for i in range(len(topping)):
        left_dict.add(topping[i])
        right_dict.add(topping[len(topping)-1-i])
        from_left[i] = len(left_dict)
        from_right[len(topping)-1-i] = len(right_dict)
    # print(from_left)
    # print(from_right)
    for i in range(1,len(topping)-1):
        if from_left[i] == from_right[i+1]:
            answer += 1
    return answer