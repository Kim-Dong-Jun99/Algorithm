import sys
T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    nums = list(map(int, sys.stdin.readline().split()))
    value=0
    max=0
    for i in range(len(nums)-1,-1,-1):
        if(nums[i] > max):
            max = nums[i]
        else:
            value+=max-nums[i]
    print(value)