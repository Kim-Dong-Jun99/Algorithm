import sys
from typing import *

input = sys.stdin.readline

def test():
    n, k, t, m = map(int, input().split())
    problems = [list(map(int, input().split())) for _ in range(m)]
    scores = [[0 for _ in range(k+1)] for _ in range(n+1)]
    solved = [[0 for _ in range(k+1)] for _ in range(n+1)]
    answered = [-1 for _ in range(n+1)]
    for index, value in enumerate(problems):
        id, problem, score = value[0], value[1], value[2]
        answered[id] = index
        solved[id][problem] += 1
        scores[id][problem] = max(scores[id][problem], score)

    ranks : List[Tuple[int, int, int, int]] = []

    for id in range(1, n+1):
        ranks.append((id, sum(scores[id]), sum(solved[id]), answered[id]))
    ranks.sort(key = lambda rank :  (-rank[1], rank[2], rank[3]))
    for i, rank in enumerate(ranks):
        if rank[0] == t:
            print(i+1)
if __name__ == "__main__":
    T : int = int(input())
    for i in range(T):
        test()

