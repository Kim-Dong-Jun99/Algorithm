import sys
import math

N: int
A: list
operands: list
used_operands: list
max_result: int
min_result: int


def init():
    global N, A, operands, used_operands, max_result, min_result
    N = int(sys.stdin.readline())
    A = list(map(int, sys.stdin.readline().split()))

    operands = list(map(int, sys.stdin.readline().split()))
    used_operands = [-1] * (N - 1)

    max_result = -1_000_000_000
    min_result = 1_000_000_000


def solution():
    dfs(0, used_operands, operands)
    print(max_result)
    print(min_result)


def dfs(index: int, operand_array: list, remain: list):
    global max_result, min_result
    if index == N - 1:
        temp_answer = calculate(operand_array)
        max_result = max(temp_answer, max_result)
        min_result = min(temp_answer, min_result)
        return

    for i in range(4):
        if remain[i] != 0:
            remain[i] -= 1
            operand_array[index] = i

            dfs(index + 1, operand_array, remain)

            remain[i] += 1
            operand_array[index] = -1


def calculate(operand_array: list) -> int:
    operation = []
    for i in range(N):
        operation.append(A[i])
        if i != N - 1:
            operation.append(operand_array[i])
    result = 0
    for i in range(1, len(operation), 2):
        if i == 1:
            result = get_value(operation[0], operation[1], operation[2])
        else:
            result = get_value(result, operation[i], operation[i + 1])
    return result


def get_value(left: int, operand: int, right: int) -> int:
    if operand == 0:
        return left + right
    elif operand == 1:
        return left - right
    elif operand == 2:
        return left * right
    else:
        if left < 0 < right:
            return - math.floor(-left / right)
        else:
            return math.floor(left / right)


if __name__ == "__main__":
    init()
    solution()
