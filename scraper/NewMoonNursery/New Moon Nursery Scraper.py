import csv
import fileinput
import urllib.request as req
from contextlib import closing
from time import sleep

from bs4 import BeautifulSoup
from requests import get
from requests.exceptions import RequestException


"""
Written by Haseebullah Siddiqui
A web scraper for http://www.newmoonnursery.com/ with slightly modified code
from https://realpython.com/python-web-scraping-practical-introduction/
for the three functions at the top
"""


def simple_get(url):
    try:
        with closing(get(url, stream=True)) as resp:
            if is_good_response(resp):
                return resp.content
            else:
                return None

    except RequestException as e:
        log_error('Error during requests to {0} : {1}'.format(url, str(e)))
        return None


def is_good_response(resp):
    content_type = resp.headers['Content-Type'].lower()
    return (resp.status_code == 200
            and content_type is not None
            and content_type.find('html') > -1)


def log_error(e):
    print(e)


# A list of plant URLs taken from the HTML of each plant category at http://www.newmoonnursery.com/
# Each line in each text file has something like: plant/Agastache-Blue-Fortune
PlantList_00 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/00_AllPlants.txt'
PlantList_01 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/01_AlkalineSoilTolerantList.txt'
PlantList_02 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/02_BirdButterflyBugGardensList.txt'
PlantList_03 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/03_DroughtTolerantList.txt'
PlantList_04 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/04_GrassesList.txt'
PlantList_05 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/05_GroundhogResistantList.txt'
PlantList_06 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/06_LandscapeOrnamentalsList.txt'
PlantList_07 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/07_MeadowList.txt'
PlantList_08 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/08_NorthAmericanNativeList.txt'
PlantList_09 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/09_PerennialsList.txt'
PlantList_10 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/10_PhytoremediationList.txt'
PlantList_11 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/11_RabbitResistantList.txt'
PlantList_12 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/12_RainGardensList.txt'
PlantList_13 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/13_RestorationConservationList.txt'
PlantList_14 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/14_RooftopGardenList.txt'
PlantList_15 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/15_ShrubList.txt'
PlantList_16 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/16_SoilStabilizationList.txt'
PlantList_17 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/17_StormwaterManagementList.txt'
PlantList_18 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/18_VinesList.txt'
PlantList_19 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/19_WetlandsList.txt'
PlantList_20 = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Lists/20_WoodlandList.txt'

csvFields = ['Botanical Name', 'Height',
             'Spread', 'Spacing', 'USDA Hardiness Zone', 'Bloom Color', 'Common Name',
             'Soil Moisture Preference', 'Exposure', 'Flowering Months', 'Attracts Wildlife',
             'Extra Attributes', 'Deer Resistant', 'Foliage Color', 'Growth Rate', 'Salt Tolerance',
             'Season of Interest', 'Phytoremediation']
csvRows = []
csvFileName = 'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Results/PlantList_00.csv'
basicAttributeSoup = []
oneString = ""

# change this for whatever plant list you want to get data for
pathToPlantList = PlantList_00

urlBase = 'http://www.newmoonnursery.com/'

listOfPlantURLs = []

with open(pathToPlantList, newline="") as fp:
    for line in fp:
        listOfPlantURLs.append(urlBase + line.strip())

for url in listOfPlantURLs:
    print("Current URL: " + url)
    try:
        raw_html = simple_get(url)
        soup = BeautifulSoup(raw_html, 'html.parser')
    except:
        print("Error occurred")

    # get plant name
    botanicalNameSoup = soup.find('h2', attrs={'class': 'layoutA'})
    botanicalName = botanicalNameSoup.text.strip()
    oneString += botanicalName + ";"
    # end get plant name

    # get all basic plant attributes
    basicAttributeSoup = soup.find_all('div', attrs={'class': 'attribute'})
    basicAttributeCleanList = []
    # add plant botanical name to list
    basicAttributeCleanList.append(botanicalName)

    for element in basicAttributeSoup:
        basicAttributeCleanList.append(element.text.strip())

    result = [iter for iter in basicAttributeCleanList if "Height" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in basicAttributeCleanList if "Spread" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in basicAttributeCleanList if "Spacing" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in basicAttributeCleanList if "USDA Hardiness" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in basicAttributeCleanList if "Bloom Color" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    # get plant common name
    commonNameSoup = soup.find('h3', attrs={'class': 'layoutA'})
    commonName = commonNameSoup.text.strip()
    oneString += commonName + ";"
    # end get plant common name

    # get more plant attributes
    extraAttributeSoup = soup.find_all('div', attrs={'class': 'charBlock'})
    extraAttributeCleanedList = []

    for element in extraAttributeSoup:
        extraAttributeCleanedList.append(
            element.text.strip().replace("\n\n", "", 10000).replace("\n", ",", 10000))

    result = [iter for iter in extraAttributeCleanedList if "Soil Moisture" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "Exposure" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [
        iter for iter in extraAttributeCleanedList if "Flowering Month" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [
        iter for iter in extraAttributeCleanedList if "Attracts Wildlife" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "Attributes" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "Deer Resistant" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "Foliage Color" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "Growth Rate" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "Salt Tolerance" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [
        iter for iter in extraAttributeCleanedList if "Season of Interest" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    result = [iter for iter in extraAttributeCleanedList if "remediation" in iter]
    oneString += oneString.join(result) + \
        ";" if result != [] else oneString.join(";")

    # clean up the string
    oneString = oneString.replace(" (No Advance Orders)", "")

    oneString = oneString.replace("Height: ", "")
    oneString = oneString.replace("Spacing: ", "")
    oneString = oneString.replace("Spread: ", "")
    oneString = oneString.replace("USDA Hardiness Zone: ", "")
    oneString = oneString.replace("Bloom Color: ", "")

    oneString = oneString.replace("Height:", "")
    oneString = oneString.replace("Spacing:", "")
    oneString = oneString.replace("Spread:", "")
    oneString = oneString.replace("USDA Hardiness Zone:", "")
    oneString = oneString.replace("Bloom Color:", "")

    oneString = oneString.replace("Soil Moisture Preference", "")
    oneString = oneString.replace("Exposure", "")
    oneString = oneString.replace("Flowering Months", "")

    oneString = oneString.replace("Attracts Wildlife", "")
    oneString = oneString.replace("Attributes", "")
    oneString = oneString.replace("Deer Resistant", "", 1)
    oneString = oneString.replace("Foliage Color", "")
    oneString = oneString.replace("Growth Rate", "")
    oneString = oneString.replace("Salt Tolerance", "")
    oneString = oneString.replace("Season of Interest (Foliage)", "")
    oneString = oneString.replace("Season of Interest", "")
    oneString = oneString.replace("Phytoremediation", "")

    for x in range(50):
        oneString = oneString.replace(":", "")
        oneString = oneString.replace(";;", "; ;")
    # end string cleaning

    print("Cleaned results: " + oneString)
    csvRows.append([oneString])
    oneString = ""

    # get images for each plant
    imageSoup = soup.find('a', attrs={'class': 'thumbSwitch layoutF'})
    test = imageSoup['href']

    try:
        req.urlretrieve(
            urlBase + imageSoup['href'],  'C:/Users/ts140/eclipse-workspace-java-hp/team-11-2/scraper/NewMoonNursery/Images/' + botanicalName + '.jpg')
        print("Image obtained for plant " + botanicalName)
    except:
        print("An error occurred while trying to retrieve image for plant " + botanicalName)
    # end get plant images

    sleep(1)

# Write the CSV
with open(csvFileName, 'w', newline="") as csvfile:
    csvwriter = csv.writer(csvfile, quoting=csv.QUOTE_NONE,
                           escapechar='\\', delimiter=";")
    csvwriter.writerow(csvFields)
    csvwriter.writerows(csvRows)

# This replaces all instances of "\" with "" in the given file
fileToEdit = csvFileName
with fileinput.FileInput(fileToEdit, inplace=True) as file:
    for line in file:
        print(line.replace("\\", ""), end='')
