import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

students = []
for i in range(n):
    students.insert(len(students)-ns[i], i+1)
    
print(*students)