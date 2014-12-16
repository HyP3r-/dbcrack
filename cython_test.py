import time

import tester_dbisam


DATA = "95 A8 A9 82 CC 89 4C 2A 24 10 5F 32 C1 8C 88 FE"
DATA = [int(x, 16) for x in DATA.split(" ")]
DATA = bytearray(DATA)

LETTERS = [i for i in range(48, 57)]
LETTERS = bytearray(LETTERS)

pre = time.time()
result, password = tester_dbisam.probe_start(0,
                                             400000,
                                             LETTERS,
                                             DATA)
post = time.time()

print(result, password, post - pre)

# THIS IS THE CONCEPT OF THE SYSTEM:

# data = "a bunch of bytes like a hash"

# password = "1234578"
# d = md5(password)
# d = b'%\xd5Z\xd2\x83\xaa@\n\xf4d\xc7mq<\x07\xad'

# -> blowfish(Password, Data)
# data = "E2 69 8A C4 34 86 38 3B EC D0 C4 32 F4 BA D7 E3"
# e = blowfish_decrypt(d, data)

# e == data
# d = b'%\xd5Z\xd2\x83\xaa@\n\xf4d\xc7mq<\x07\xad'