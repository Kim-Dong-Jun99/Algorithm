from collections import deque
def solution(priorities, location):
    queue = deque()
    for i in range(len(priorities)):
        queue.append([i, priorities[i]])
    index = 1
    while queue:
        # print(queue)
        max_index = 0
        max_v = 0
        for i in range(len(queue)):
            if queue[i][1] > max_v:
                max_index = i
                max_v = queue[i][1]
        count = 0
        temp_q = deque()
        while count < max_index:
            temp_q.append(queue.popleft())
            count += 1
        popped = queue.popleft()
        if popped[0] == location:
            return index
        index += 1
        while temp_q:
            queue.append(temp_q.popleft())