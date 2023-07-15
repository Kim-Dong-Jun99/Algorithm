from heapq import heappush, heappop

def solution(operations):
    heap = []
    for operation in operations:
        cmd, num = operation.split()
        to_append = int(num)
        if cmd == "I":
            heappush(heap, to_append)
        else:
            if num == "1":
                if heap:
                    temp = []
                    while len(heap) > 1:
                        heappush(temp, heappop(heap))
                    heap = temp
            else:
                if heap:
                    heappop(heap)
    if heap:
        if len(heap) == 1:
            return [heap[0], heap[0]]
        answer = [0,0]
        answer[1] = heappop(heap)
        while len(heap) > 1:
            heappop(heap)
        answer[0] = heap[0]
        return answer
        
    return [0,0]