import requests
import pandas as pd

api_key = 'Ao-Na54blixaRRNxpF-2xNOlWBptsOCtbvSixSuqvxcAjk2cMoZtx39ME7nAixtB'


def geocode_address(address, api_key):
    url = f"http://dev.virtualearth.net/REST/v1/Locations"
    params = {
        "query": address,
        "key": api_key
    }

    response = requests.get(url, params=params)
    data = response.json()

    try:
        location = data["resourceSets"][0]["resources"][0]["point"]["coordinates"]
        return location
    except (KeyError, IndexError):
        return None


def batch_geocode(addresses, api_key):
    coordinates = []

    for address in addresses:
        location = geocode_address(address, api_key)
        if location:
            coordinates.append(location)
        else:
            coordinates.append(None)

    return coordinates


df = pd.read_csv("wineries.csv")

names = df['Name']
addresses = df['Address'] + ", " + df['Location'] + ", MK"
coordinates = batch_geocode(addresses, api_key)
latitudes = []
longitudes = []

for coordinate in coordinates:
    latitudes.append(coordinate[0])
    longitudes.append(coordinate[1])

data = {
    'Name': names,
    'Latitude': latitudes,
    'Longitude': longitudes
}

df = pd.DataFrame(data)
file_name = "wineries_with_coordinates.csv"
df.to_csv(file_name, index=False)
