import sys
from typing import *

input = sys.stdin.readline
print = sys.stdout.write

if __name__ == '__main__':
    n, d = map(int, input().split())
    roads = [list(map(int, input().split())) for _ in range(n)]
    dijkstra = [i for i in range(d+1)]
    dijkstra[0] = 1
    cur : List[Tuple[int, int]] = [(0, 0)]
    while cur:
        temp : List[Tuple[int, int]] = []
        for position, distance in cur:
            if distance >= dijkstra[position]:
                continue
            dijkstra[position] = distance
            for i, index in enumerate(range(position, d+1)):
                dijkstra[index] = min(dijkstra[index],dijkstra[position] + i)
            for start, end, shortDistance in roads:
                if start < position:
                    continue
                if end > d:
                    continue
                if dijkstra[end] > dijkstra[position] + (start - position) + shortDistance:
                    temp.append((end, dijkstra[position] + (start - position) + shortDistance))
        cur = temp
    print('%d'%dijkstra[d])
