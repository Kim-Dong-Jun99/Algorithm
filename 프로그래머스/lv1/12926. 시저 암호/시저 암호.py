def solution(s, n):
    answer = ''
    for i in s:
        ascii_num = ord(i)
        if 65 <= ascii_num and ascii_num <= 90:
            if ascii_num + n > 90:
                ascii_num = 64 + (ascii_num + n - 90) 
            else:
                ascii_num += n
            answer += chr(ascii_num)
        elif 97 <= ascii_num and ascii_num <= 122:
            if ascii_num + n > 122:
                ascii_num = 96 + (ascii_num + n - 122) 
            else:
                ascii_num += n
            answer += chr(ascii_num)
        else:
            answer += i
    return answer