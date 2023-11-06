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
        search_address = str(address).split(',')[0] + str(address).split(',')[2] if str(address)[:2] == 'с.' else address
        location = geocode_address(search_address, api_key)
        if location:
            coordinates.append(location)
        else:
            coordinates.append(None)

    return coordinates


df = pd.read_csv("../clean_data/wineries.csv")

names = df['Name']
addresses = df['Address'] + ", MK" if str(df['Address'])[:2] == 'с.' else df['Address'] + ", " + df['Location'] + ", MK";
coordinates = batch_geocode(addresses, api_key)
latitudes = []
longitudes = []

for coordinate in coordinates:
    latitudes.append(coordinate[0])
    longitudes.append(coordinate[1])

df['Latitude'] = latitudes
df['Longitude'] = longitudes
file_name = "../clean_data/wineries_with_coordinates_final.csv"
df.to_csv(file_name, index=False)
