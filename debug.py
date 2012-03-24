import time

class Timer():
    def __enter__(self):
        self.start = time.time()

    def __exit__(self, *a):
        print time.time() - self.start
