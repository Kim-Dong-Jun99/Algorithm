import sys

score = int(sys.stdin.readline())

if 90 <= score:
    sys.stdout.write("A\n")
elif 80 <= score:
    sys.stdout.write("B\n")
elif 70 <= score:
    sys.stdout.write("C\n")
elif 60 <= score:
    sys.stdout.write("D\n")
else:
    sys.stdout.write("F\n")