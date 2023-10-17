import heapq as hq
import collections
import sys
def solution(n, start, end, roads, traps):
    answer = 0
    graph = collections.defaultdict(list)
    dijkstra = [dict() for _ in range(n+1)]
    trap_i = {t: i for i, t in enumerate(traps)}
    trap_state = "0"*len(traps)
    # 0 is not revered condition / we can follow original direction
    # 1 is reversed condition / we can follow opposite direction

    for src, dest, w in roads:
        graph[src].append((w, dest, 0))
        if (src in traps) or (dest in traps):
            graph[dest].append((w, src, 1))

    heap = [(0, start, trap_state)]
    dijkstra[start][trap_state] = 0
    min_w = sys.maxsize

    while heap:
        w, src, state = hq.heappop(heap)

        if src in traps:
            src_s = int(state[trap_i[src]])
        else:
            src_s = 0

        for dest_w, dest, is_traped in graph[src]:
            if dest not in traps:
                dest_s = 0
            else:
                dest_s = int(state[trap_i[dest]])

            if (src_s ^ dest_s) == 1:
                if is_traped == 0:
                    continue
            else:
                if is_traped == 1:
                    continue

            total_w = w + dest_w

            if dest not in traps:
                dest_state = state
            else:
                n_state = state
                n_state = state[:trap_i[dest]] + str(int(state[trap_i[dest]])^1) +                       state[trap_i[dest]+1:]
                dest_state = n_state

            if total_w < min_w:
                if (dest_state not in dijkstra[dest]) or (dijkstra[dest][dest_state] > total_w):
                    if total_w < min_w:
                        dijkstra[dest][dest_state] = total_w
                    if dest == end:
                        min_w = min(total_w, min_w)
                    else:
                        hq.heappush(heap, (total_w, dest, dest_state))
    return min_w
