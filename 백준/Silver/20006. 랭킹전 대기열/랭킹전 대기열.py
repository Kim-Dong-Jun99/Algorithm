import sys
from typing import *

input = sys.stdin.readline
print = sys.stdout.write

if __name__ == '__main__':
    p, m = map(int, input().split())
    rooms : List[Tuple[int, int, List[Tuple[int, str]]]] = []
    for _ in range(p):
        level, name = input().split()
        level = int(level)
        findRoom : bool = False
        for room in rooms:
            low, high = room[0], room[1]
            players = room[2]
            if low <= level <= high and len(players) < m:
                findRoom = True
                players.append((level, name))
                break
        if not findRoom:
            rooms.append((level-10, level+10, [(level, name)]))
    for room in rooms:
        players = room[2]
        if len(players) == m:
            print("Started!\n")
        else:
            print("Waiting!\n")
        players.sort(key = lambda x : x[1])
        for level, name in players:
            print('%d %s\n'%(level, name))

