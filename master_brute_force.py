import time

import config
from server import TaskManager


def __password_creator(m):
    job_queue, result_queue = m.get_job_queue(), m.get_result_queue()
    print_time = time.time()

    while True:
        # Print stats
        if time.time() - print_time > 15:
            # TODO Print here more details
            print("Current Password Start " + str(config.PASSWORD_START))
            print_time = time.time()

        # Check if results are here
        while not result_queue.empty():
            result, password = result_queue.get()
            if result:
                return result, password

        # Check if we need to create more tasks
        if job_queue.qsize() < 100 and config.PASSWORD_START < len(
                config.LETTERS) ** config.RANGE_MAX:
            job_queue.put((config.PASSWORD_START,
                           config.PASSWORD_START + config.PASSWORD_TASK_SIZE,
                           config.LETTERS,
                           config.DATA))
            config.PASSWORD_START += config.PASSWORD_TASK_SIZE
        else:
            if config.PASSWORD_START > len(config.LETTERS) ** config.RANGE_MAX \
                    and job_queue.qsize() == 0:
                return 0, ""

        # Wait
        time.sleep(0.1)


if __name__ == '__main__':
    TaskManager.register('get_job_queue')
    TaskManager.register('get_result_queue')
    m = TaskManager(address=(config.SERVER_IP, config.SERVER_PORT),
                    authkey=config.SERVER_PASSWORD)
    m.connect()

    t1 = time.time()
    result, password = __password_creator(m)
    t2 = time.time()
