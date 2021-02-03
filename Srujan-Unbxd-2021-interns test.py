import requests
import json
import csv
ans=[]
def convert(response):
	convert_json = response.json()
	only_products = convert_json["response"]["products"]
	for i in range(len(only_products)):
		for j in only_products[i]:
			if(type(only_products[i][j])==list):
				if(j!="unbxd_color_for_category"):
					only_products[i][j]=list(set(only_products[i][j]))
					if(len(only_products[i][j]) > 1):
						only_products[i][j]=','.join(only_products[i][j])
					else:
						only_products[i][j]=str(only_products[i][j])
				else:
					arr=[]
					for m in only_products[i][j]:
						l=m.split("::")
						arr.append(l[0])
					h=list(set(arr))
					only_products[i][j]=','.join(h)
	return only_products
for start in range(1,10):
	response = requests.get("https://search.unbxd.io/fb853e3332f2645fac9d71dc63e09ec1/demo-unbxd700181503576558/search?&q=*&rows=10&start="+str(start))
	ans.append(convert(response))
file = open('Unbxd-2021-interns test.csv', 'a+', newline ='') 
with file:     
    write = csv.writer(file) 
    write.writerows(ans)