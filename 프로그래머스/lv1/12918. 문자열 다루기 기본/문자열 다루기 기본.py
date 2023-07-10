def solution(s):
    for i in range(65,91):
        a = chr(i)
        if a in s:
            return False
        b = chr(i+32)
        if b in s:
            return False
    if 4 == len(s) or len(s) == 6:
        return True
    return False