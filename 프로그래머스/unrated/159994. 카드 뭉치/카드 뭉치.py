def solution(cards1, cards2, goal):
    """
    23:21
    """
    one_index = 0
    two_index = 0
    for i in goal:
        if one_index == len(cards1):
            if cards2[two_index] == i:
                two_index += 1
            else:
                return "No"
        elif two_index == len(cards2):
            if cards1[one_index] == i:
                one_index += 1
            else:
                return "No"
        else:
            if i == cards1[one_index]:
                one_index += 1
            elif i == cards2[two_index]:
                two_index += 1
            else:
                return "No"
    return "Yes"
            