def solution(users, emoticons):
    """
    사용자는 자신이 세워놓은 기준보다 할인 많이 하는 이모티콘 모두 구매,
    구매한 이모티콘 가격의 총합이 본인이 세워놓은 가격보다 크면 이모티콘 플러스가입
    이모티콘 할인율은 각각 다를 수도 있음
    사람들이 이모티콘 플러스 많이 가입시키는게 이득, 
    이모티콘 할인율은 10, 20, 30, 40 중 하나
    """
    answer = [0, 0]
    emoticon_combo = []
    discounts = [0.9, 0.8, 0.7, 0.6]
    emoticon_combo = [[0.9], [0.8], [0.7], [0.6]]
    for i in range(1, len(emoticons)):
        emoticon_combo = bfs(emoticon_combo,discounts)
    for case in emoticon_combo:
        temp = [0,0]
        for u_p, u_v in users:
            temp_v = 0
            convert_p = (100 - u_p)/100
            for p in range(len(emoticons)):
                if convert_p >= case[p]:
                    temp_v += case[p] * emoticons[p]
            if temp_v >= u_v:
                temp[0] += 1
            else:
                temp[1] += temp_v
        if temp[0] > answer[0]:
            answer = temp
        elif temp[0] == answer[0] and temp[1] > answer[1]:
            answer = temp
                    
    
                    
    return answer

def bfs(emoticon_combo, discounts):
    result = []
    for j in emoticon_combo:
        for d in discounts:
            to_insert = j.copy()
            to_insert.append(d)
            result.append(to_insert)
    return result
    