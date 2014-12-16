from __future__ import print_function
from multiprocessing.managers import BaseManager
from multiprocessing import JoinableQueue, Queue

import config


class TaskManager(BaseManager):
    pass


if __name__ == '__main__':
    master_socket = config.SERVER_PORT
    task_queue = JoinableQueue()
    result_queue = Queue()
    TaskManager.register('get_job_queue', callable=lambda: task_queue)
    TaskManager.register('get_result_queue', callable=lambda: result_queue)
    m = TaskManager(address=('', master_socket), authkey=config.SERVER_PASSWORD)
    print('starting queue controller, socket', master_socket)
    m.get_server().serve_forever()