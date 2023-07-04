from collections import deque

def solution(queue1, queue2):
    answer = 0
    """
    queue1, queue2의 길이가 같다. 
    """
    cur_sum1 = sum(queue1)
    cur_sum2 = sum(queue2)
    q1 = deque(queue1)
    q2 = deque(queue2)
    limit = (len(queue1) + len(queue2))*2
    while cur_sum1 != cur_sum2 and answer < limit: 
        if cur_sum2 > cur_sum1:
            cur_sum2 -= q2[0]
            cur_sum1 += q2[0]
            q1.append(q2.popleft())
        else:
            cur_sum1 -= q1[0]
            cur_sum2 += q1[0]
            q2.append(q1.popleft())
        answer += 1
    if answer == limit:
        return -1
    
    return answer