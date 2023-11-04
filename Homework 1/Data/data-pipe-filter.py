import warnings
import numpy as np
import pandas as pd
import requests
import matplotlib.pyplot as plt
from bs4 import BeautifulSoup
from IPython.display import HTML


class Pipeline:
    def __init__(self):
        self.filters = list()

    def add(self, filter):
        self.filters.extend(filter)

    def execute(self, message):
        print("Executing pipeline...")
        for message_filter in self.filters:
            message_filter(message)


def fix_address(message):
    message['Address'] = str(message['Address']).split(': ')[1]


def fix_location(message):
    message['Location'] = str(message['Location']).split(': ')[1]


def fix_phoneNumber(message):
    message['Mob. Number'] = '+389' + str(message['Mob. Number']).replace('\xa0', '')[1:]


def fix_occupation(message):
    message['Occupation'] = str(message['Occupation']).split(':')[1]


if __name__ == '__main__':
    pipeline = Pipeline()
    pipeline.add([fix_address,
                  fix_location,
                  fix_phoneNumber,
                  fix_occupation])
    wineries = pd.read_csv('./dirty_data/wineries.csv').to_dict(orient='records')
    for w in wineries:
        pipeline.execute(w)

    df = pd.DataFrame(wineries)
    df.to_csv('./clean_data/wineries.csv',index=None)
