def solution(s, skip, index):
    # print(ord(s[0]))
    # print(ord("z"))
    answer = ""
    for i in s:
        asc = ord(i)
        count = 0
        while count < index:
            while True:
                asc += 1
                if asc == 123:
                    asc = 97
                if chr(asc) not in skip:
                    break
            count += 1
        answer += chr(asc)
    return answer