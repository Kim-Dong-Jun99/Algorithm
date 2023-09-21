import sys
import math

N: int
M: int
T: int
circles: list
total_sum: int
total_count: int
spins: list
CLOCKWISE = 0


class Spin:

    def __init__(self, x: int, direction: int, k: int):
        self.x = x
        self.direction = direction
        self.k = k


class Position:

    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y


def init():
    global N, M, T, circles, total_sum, total_count, spins

    N, M, T = map(int, sys.stdin.readline().split())

    total_sum = 0
    total_count = N * M

    circles = [[0 for _ in range(M + 1)] for _ in range(N + 1)]

    for i in range(1, N + 1):
        input_list = list(map(int, sys.stdin.readline().split()))
        for j in range(1, M + 1):
            circles[i][j] = input_list[j - 1]
            total_sum += circles[i][j]

    spins = list()
    for i in range(T):
        input_list = list(map(int, sys.stdin.readline().split()))
        spins.append(Spin(input_list[0], input_list[1], input_list[2]))


def solution():
    global total_sum, total_count
    for spin in spins:
        spin_circle(spin)

        to_remove = get_position_to_remove()

        for position in to_remove:
            total_sum -= circles[position.x][position.y]
            total_count -= 1
            circles[position.x][position.y] = 0

        if len(to_remove) == 0:
            update_circle_value()
    print(total_sum)


def spin_circle(spin: Spin):
    x = spin.x

    while x <= N:
        removed = [0] * spin.k
        if spin.direction == CLOCKWISE:
            index = 0
            while index < spin.k:
                removed[index] = circles[x][M - index]
                index += 1
            index = M
            while index - spin.k >= 1:
                circles[x][index] = circles[x][index - spin.k]
                index -= 1
            index = 0
            while index < spin.k:
                circles[x][index + 1] = removed[spin.k - 1 - index]
                index += 1
        else:
            index = 0
            while index < spin.k:
                removed[index] = circles[x][index + 1]
                index += 1
            index = 1
            while index + spin.k <= M:
                circles[x][index] = circles[x][index + spin.k]
                index += 1
            index = 0
            while index < spin.k:
                circles[x][M - index] = removed[spin.k - 1 - index]
                index += 1
        x += spin.x


def get_position_to_remove() -> list:
    to_remove = list()

    for i in range(1, N + 1):
        for j in range(1, M + 1):
            if circles[i][j] != 0:
                neighbors = get_neighbors(i, j)
                local_find = False
                for neighbor in neighbors:
                    if circles[i][j] == circles[neighbor.x][neighbor.y]:
                        local_find = True
                        break
                if local_find:
                    to_remove.append(Position(i, j))

    return to_remove


def update_circle_value():
    global total_sum, total_count
    if total_count == 0: 
        return 
    mean = total_sum / total_count
    for i in range(1, N + 1):
        for j in range(1, M + 1):
            if circles[i][j] != 0:
                if circles[i][j] > mean:
                    circles[i][j] -= 1
                    total_sum -= 1
                elif circles[i][j] < mean:
                    circles[i][j] += 1
                    total_sum += 1


def get_neighbors(x: int, y: int) -> list:
    neighbors = []
    if y == 1:
        neighbors.append(Position(x, 2))
        neighbors.append(Position(x, M))
    if y == M:
        neighbors.append(Position(x, M - 1))
        neighbors.append(Position(x, 1))
    if 2 <= y <= M - 1:
        neighbors.append(Position(x, y - 1))
        neighbors.append(Position(x, y + 1))
    if x == 1:
        neighbors.append(Position(2, y))
    if x == N:
        neighbors.append(Position(N - 1, y))
    if 2 <= x <= N - 1:
        neighbors.append(Position(x - 1, y))
        neighbors.append(Position(x + 1, y))
    return neighbors


if __name__ == "__main__":
    init()
    solution()
