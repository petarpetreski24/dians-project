import warnings
import numpy as np
import pandas as pd
import requests
import matplotlib.pyplot as plt
from bs4 import BeautifulSoup
from IPython.display import HTML

requests.packages.urllib3.disable_warnings()
warnings.filterwarnings("ignore")

if __name__ == '__main__':
    zk_winery_elements = []
    for i in range(0, 80, 20):
        response = requests.get(f'https://zk.mk/search/?skip={i}&what=%D0%B2%D0%B8%D0%BD%D0%B0%D1%80%D0%B8%D1%98%D0%B0')
        html = BeautifulSoup(response.text, 'html.parser')
        zk_winery_elements.extend([x['href'] for x in html.select('.companyname')])
    wineries = []
    for element in zip(zk_winery_elements, range(0, len(zk_winery_elements))):
        winery = {}
        response = requests.get(f'https://zk.mk/{element[0]}')
        html = BeautifulSoup(response.text, 'html.parser')
        winery["ID"] = element[1]
        winery["Name"] = html.select_one('.companyname').text
        winery["Address"] = html.select('ul.details > li')[0].text
        winery["Location"] = html.select('ul.details > li')[1].text
        winery["Mob. Number"] = html.select_one('a.tcall').text
        winery["Occupation"] = html.select_one('.shortdescription').text
        wineries.append(winery)

    df = pd.DataFrame(wineries)
    df.to_csv('./dirty_data/wineries.csv',index=None)
