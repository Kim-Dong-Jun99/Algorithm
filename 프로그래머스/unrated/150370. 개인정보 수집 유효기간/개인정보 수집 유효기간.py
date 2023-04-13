def solution(today, terms, privacies):
    """
    19:56
    한달 = 28
    일년 = 28 * 12
    한달 = 28 
    하루 = day
    오늘 - 유효기간 만료일 < 유효기간 달 * 28 = 보관 else 파기
    """
    answer = []
    today_list = today.split(".")
    today_num = int(today_list[0]) * 28 * 12 + int(today_list[1]) * 28 + int(today_list[2])
    expire_dict = {}
    for string in terms:
        term, valid = string.split()
        expire_dict[term] = int(valid)
    for i in range(len(privacies)):
        date, policy = privacies[i].split()
        policy_month = expire_dict[policy]
        date_list = date.split(".")
        date_num = int(date_list[0])*28*12 + int(date_list[1])*28 + int(date_list[2])
        if today_num - date_num < policy_month * 28:
            continue
        else:
            answer.append(i + 1)
    return answer