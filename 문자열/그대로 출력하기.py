while True:
    try:
        print(input())
    except EOFError as e:
        break