from collections import defaultdict
def solution(id_list, report, k):
    answer = []
    singo = defaultdict(lambda : 0)
    maillist = defaultdict(lambda : set())
    sued = defaultdict(lambda:set())
    for given in report:
        a,b = given.split()
        if a not in maillist[b]:
            singo[b] += 1
        maillist[b].add(a)
        sued[a].add(b)
    for i in id_list:
        temp = 0
        for j in sued[i]:
            if singo[j] >= k:
                temp += 1
        answer.append(temp)
            
    return answer