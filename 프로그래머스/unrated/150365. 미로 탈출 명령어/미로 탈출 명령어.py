from collections import defaultdict
def solution(n, m, x, y, r, c, k):
    answer = ''
    x -= 1
    y -= 1
    r -= 1
    c -= 1
    x_diff = r - x
    y_diff = c - y
    abs_x = abs(x_diff)
    abs_y = abs(y_diff)
    to_go = defaultdict(int)
    if abs_x + abs_y > k:
        return "impossible"
    if (k - abs_x - abs_y) % 2 == 1:
        return "impossible"
    extra_move = (k - abs_x - abs_y)//2
    if x_diff < 0:
        to_go["u"] += abs_x
        if y_diff < 0:
            to_go["l"] += abs_y
        else:
            to_go["r"] += abs_y
    else:
        to_go["d"] += abs_x
        if y_diff < 0:
            to_go["l"] += abs_y
        else:
            to_go["r"] += abs_y
    
    while to_go["d"] > 0 and x + 1 < n:
        answer += "d"
        to_go["d"] -= 1
        x += 1
    while x + 1 < n and extra_move > 0:
        answer += "d"
        to_go["u"] += 1
        x += 1
        extra_move -= 1
    while to_go["l"] > 0 and y > 0:
        answer += "l"
        to_go["l"] -= 1
        y -= 1
    while y - 1 >= 0 and extra_move > 0:
        answer += "l"
        to_go["r"] += 1
        y -= 1
        extra_move -= 1
    if extra_move == 0:
        answer += "r"*to_go["r"]
        answer += "u"*to_go["u"]
    else:
        while extra_move > 0:
            answer += "rl"
            extra_move -= 1
        answer += "r" * to_go["r"]
        answer += "u" * to_go["u"]    
            
    return answer