import sys
nums = [int(sys.stdin.readline()) for _ in range(9)]
print(max(nums))
print(nums.index(max(nums))+1)