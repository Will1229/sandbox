def test1(s):
    if s is None or s == '':
        return s
    value = ''
    current = None
    count = 1
    for letter in s:
        if current is None:
            current = letter
        elif current == letter:
            count += 1
            continue
        else:
            value += current + str(count)
            current = letter
            count = 1
    value += current + str(count)
    return value
