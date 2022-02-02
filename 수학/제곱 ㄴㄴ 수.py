import sys
left,right = map(int,sys.stdin.readline().split())
nopower = [0 for _ in range(right-left+1)]
npnum = right-left+1

for i in range(2,int(right**0.5)+1):
    # check = True
    # for j in range(2,int(i**0.5)+1):
        # if i%j == 0:
        #     check = False
        #     break

    temp = i**2
    s = left // temp
    temp = temp * s
    while temp <= right:
        if temp >= left:
            if nopower[temp-left] == 0:
                npnum -= 1
            nopower[temp-left] = 1
        temp += i**2

print(npnum)

