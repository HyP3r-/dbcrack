from multiprocessing import cpu_count, Process

from server import TaskManager
import tester_dbisam
import config


def __worker_function(job_queue, result_queue):
    while True:
        password_start, password_end, letters, data = job_queue.get()
        result, password = tester_dbisam.probe_start(password_start,
                                                     password_end,
                                                     letters,
                                                     data)
        result_queue.put((result, password))
        job_queue.task_done()


def __start_workers(m):
    job_queue, result_queue = m.get_job_queue(), m.get_result_queue()
    nr_of_processes = cpu_count()
    processes = [Process(target=__worker_function,
                         args=(job_queue, result_queue))
                 for i in range(nr_of_processes)]
    for p in processes:
        p.start()
    return nr_of_processes


if __name__ == "__main__":
    TaskManager.register("get_job_queue")
    TaskManager.register("get_result_queue")
    m = TaskManager(address=(config.SERVER_IP, config.SERVER_PORT),
                    authkey=config.SERVER_PASSWORD)
    m.connect()
    nr_of_processes = __start_workers(m)
    print(nr_of_processes, "workers started")