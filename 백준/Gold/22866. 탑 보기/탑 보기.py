import sys
input = sys.stdin.readline
print = sys.stdout.write

def sol(flag):
    stack = [(100001, 0)]       
    if flag:
        lst.reverse()       
    for i in lst:
        while maps[i] >= stack[-1][0]:     
            stack.pop()
        else:
            count[i] += len(stack) - 1      

            match flag:
                case True:
                    what_right = stack[-1][1]      
                    if count[i]:
                        if what[i] and what_right:
                            left = abs(i + 1 - what[i])     
                            right = abs(what_right - i - 1)     
                            if left > right:        
                                what[i] = what_right
                        elif what_right:
                            what[i] = what_right
                case False:
                    what[i] = stack[-1][1]      
            stack.append((maps[i], i + 1))      


if __name__ == "__main__":
    n = int(input())
    maps = list(map(int, input().split()))
    lst = [*range(n)]

    count = [0] * n
    what = [0] * n

    sol(False)
    sol(True)

    for i in range(n):
        print("%d " % count[i])
        if count[i]:
            print("%d" % what[i])
        print("\n")