import sys
# print(sys.version)
T = int(sys.stdin.readline())

for _ in range(T):
	N = int(sys.stdin.readline())
	tree = list(map(int, sys.stdin.readline().split()))
	tree.sort()
	left_index = 0
	right_index = 1
	result = tree[1] - tree[0]
	if len(tree) % 2 == 1:
		# 01 23 4
		while right_index + 2 <= len(tree):
			left_val = tree[left_index+2] - tree[left_index]
			if result < left_val:
				result = left_val
			left_index += 2
			if right_index+2 == len(tree):
				right_val = tree[right_index+1] - tree[right_index]
			else:
				right_val = tree[right_index+2] - tree[right_index]
			if result < right_val:
				result = right_val
			right_index += 2
		print(result)
	else:
		while right_index + 2 < len(tree):
			left_val = tree[left_index+2] - tree[left_index]
			if result < left_val:
				result = left_val
			left_index += 2
			right_val = tree[right_index+2] - tree[right_index]
			if result < right_val:
				result = right_val
			right_index += 2
			if right_index + 2 > len(tree):
				if result < tree[right_index] - tree[left_index]:
					result = tree[right_index] - tree[left_index]
		print(result)
