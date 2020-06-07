import urllib.request
import os
import csv
import urllib
path = os.getcwd()
path += "/run_results.csv"

url_list = []
urls = open(path)
with open(path) as f:
    for row in f:
        url_list.append(row)
del url_list[0]
def import_img(url, file_path, file_name):
    full_path = file_path + file_name + ".jpg"
    urllib.request.urlretrieve(url, full_path)
index = 1
for url in urls:
    import_img(url, "images/", str(index))
    index = index + 1
