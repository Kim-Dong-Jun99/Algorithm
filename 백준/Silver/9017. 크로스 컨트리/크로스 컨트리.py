import sys
from typing import *
from collections import defaultdict

input = sys.stdin.readline

def getWinner():
    teamMap = defaultdict(lambda : 0)
    visited = defaultdict(lambda : 0)
    scoreMap = defaultdict(lambda : 0)
    possible : List[Tuple[int, int, int]] = []
    for team in teams:
        teamMap[team] += 1
    ranked : int = 0
    for team in teams:
        if teamMap[team] >= 6:
            ranked += 1
            visited[team] += 1
            if visited[team] < 5:
                scoreMap[team] += ranked
            elif visited[team] == 5:
                possible.append((scoreMap[team], ranked, team))
    possible.sort(key = lambda x : (x[0], x[1]))
    print(possible[0][2])

if __name__ == "__main__":
    T : int = int(input())
    for _ in range(T):
        N : int = int(input())
        teams : List[int] = list(map(int, input().split()))
        getWinner()
