# BruteForce Min/Max
RANGE_MIN = 1
RANGE_MAX = 10

# Letters and Numbers for BruteForce
LETTERS = [i for i in range(65, 91)] + \
          [i for i in range(97, 123)] + \
          [i for i in range(48, 58)]

# Stored Hash in the Config File
DATA = "3F ED F4 A6 32 76 43 F0 95 64 AF 9F 36 C1 1F 92"
DATA = [int(x, 16) for x in DATA.split(" ")]

# Prepare Data
DATA = bytearray(DATA)
LETTERS = bytearray(LETTERS)

# Where to start and Task size
PASSWORD_START = 0
PASSWORD_TASK_SIZE = 1000000

# Server Variables
SERVER_IP = "192.168.2.110"
SERVER_PORT = 1234
SERVER_PASSWORD = b"myComplexPassword"
