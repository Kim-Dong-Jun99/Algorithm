import sys

year = int(sys.stdin.readline())


def sol(year):
    if year % 4 != 0:
        return 0
    else :
        if year % 100 == 0:
            if year % 400 == 0:
                return 1
            else:
                return 0
        else:
            return 1

print(sol(year))
