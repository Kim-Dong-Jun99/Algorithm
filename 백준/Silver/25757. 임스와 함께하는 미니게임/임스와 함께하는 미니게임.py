import sys

inputArr : list
N : int
type : str
names : set

def init():
    global inputArr, N, type, names
    inputArr = sys.stdin.readline().split()
    N = int(inputArr[0])
    type = inputArr[1]
    names = {sys.stdin.readline() for _ in range(N)}

def solve():
    global inputArr, N, type, names
    if type == "Y" :
        print(len(names))
    elif type == "F":
        print(len(names) // 2)
    else:
        print(len(names) // 3)

if __name__ == "__main__":
    init()
    solve()


