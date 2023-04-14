from heapq import heappop, heappush
def solution(cap, n, deliveries, pickups):
    """
    9:17
    집과 집 사이 거리는 1
    n = 집의 개수
    deliveries 집마다 배달해야될 택배
    pickups 수거해야될 택배들
    """
    answer = 0
    delivery_heap = []
    pickup_heap = []
    for i in range(n):
        if deliveries[i] != 0:
            heappush(delivery_heap, [-i-1, deliveries[i]])
        if pickups[i] != 0:
            heappush(pickup_heap, [-i-1, pickups[i]])
    while delivery_heap or pickup_heap:
        if delivery_heap != [] and pickup_heap != []:
            if delivery_heap[0][0] < pickup_heap[0][0]:
                answer += -2 * delivery_heap[0][0]
            else:
                answer += -2 * pickup_heap[0][0]
            can_carry = cap
            while delivery_heap and can_carry - delivery_heap[0][1] >=0:
                can_carry -= delivery_heap[0][1]
                heappop(delivery_heap)
                
            if can_carry != 0 and delivery_heap != []:
                delivery_heap[0][1] -= can_carry
            can_carry = cap
            while pickup_heap and can_carry - pickup_heap[0][1] >= 0:
                can_carry -= pickup_heap[0][1]
                heappop(pickup_heap)
                
            if can_carry != 0 and pickup_heap != []:
                pickup_heap[0][1] -= can_carry
        elif delivery_heap == []:
            answer += -2 * pickup_heap[0][0]
            can_carry = cap
            while pickup_heap and can_carry - pickup_heap[0][1] >= 0:
                can_carry -= pickup_heap[0][1]
                heappop(pickup_heap)
                
            if can_carry != 0 and pickup_heap != []:
                pickup_heap[0][1] -= can_carry
        else:
            answer += -2 * delivery_heap[0][0]
            can_carry = cap
            while delivery_heap and can_carry - delivery_heap[0][1] >=0:
                can_carry -= delivery_heap[0][1]
                heappop(delivery_heap)
                
            if can_carry != 0 and delivery_heap != []:
                delivery_heap[0][1] -= can_carry
            
        
            
    return answer