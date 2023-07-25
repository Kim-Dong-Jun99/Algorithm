import heapq
import sys
import copy

input = sys.stdin.readline
MIIS = lambda : map(int, input().split())

T = int(input())
for _ in range(T):
    N, M = MIIS()

    check_points = []

    customers = list(MIIS())

    drivers = []
    for _ in range(M):
        tmp = list(MIIS())
        drivers.append(tmp)

    customers.sort()
    drivers.sort()

    # 운전자가 승객 태울 수 있으면 태우기.
    # 태울 때, 끝나는 범위가 작은 사람 먼저!

    possible_drivers = []
    ans = 0
    idx = 0

    for customer in customers: # 승객이 가고 싶어하는 곳 하나씩 보면서

        # 그 곳이 택시 기사가 태울 의향이 있는 시작 범위와 일치하거나 작으면
        while idx < M and customer >= drivers[idx][0]:
                heapq.heappush(possible_drivers, drivers[idx][1])  # 승객이 탈 수 있는 끝 범위를 넣는다.
                idx += 1

        # 끝 범위까지 승객 만나지 못한 택시 빼기
        while possible_drivers:
            if possible_drivers[0] < customer:
                heapq.heappop(possible_drivers)
            else:
                break

        # 승객이 택시 탈 수 있으면
        if possible_drivers:
            ans += 1
            heapq.heappop(possible_drivers)

    print(ans)