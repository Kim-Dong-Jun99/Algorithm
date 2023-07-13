def solution(nums):
    unique = set(nums)
    
    return min(len(nums) // 2, len(unique))