# ﻿# GoGlides Search Engine's README #

## Getting Started ##

Clone this project.

### Requirements ###

* JAVA- JDK 8
* Elasticsearch 5.1.2 from https://www.elastic.co/downloads/past-releases/elasticsearch-5-1-2 
* Download latest Tomcat server
   
### Installation of Elasticsearch ###

* Download and unzip the Elasticsearch official distribution.
* Run bin/elasticsearch on unix, or bin\elasticsearch.bat on windows.


### Data Preparation: ###

* Run setup.sh file under demo-dataset folder

demo-dataset folder contains:-

* setup.json:- It contains mapping for type “listing”
* goglides_dev_test_bulk.json:- It contains demo dataset
* setup.sh:- It is executable file which first creates index goglides_dev_test then run setup.json file and atlast populate listing type with demo dataset from goglides_dev_test_bulk.json file.
     

### CodeBase: ###

* Import GoGlidesSearchEngine maven project into IDE.
* Update Maven Project
* Run Maven clean and install
* Run on Tomcat Server

Get Request:-
Server url:- http://<servername>:<serverport>/GoGlidesSearchEngine/goglides/getsearch?keyword=paragliding

Pass some Json parameters to your server as follows:-
Server url:- http://<servername>:<serverport>/GoGlidesSearchEngine/goglides/search

### Json Parameters: ###


```
#!json

{
    "search_params": {
        "keyword": "paragliding",
        "from_date": "",
        "to_date": "",
		"no_of_guests": "2",
		"price_range_min": "40.00",
		"price_range_max": "160.00",
		"price": "",
		"schedule": "10:00am",
		"duration": "30",
		"rating": "",
		"nationality": "nepali",
		"location": "pokhara",
		"latitude": "",
		"longitude": "",
		"neighbourhood": "",
		"age_group": "adult",
		"listing_type" : "tandem"		
    }
}
```


### Result Format: ###


```
#!json

{  
   "result_time":"18ms",
   "result_count":3,
   "results":[  
      {  
         "result":{  
            "duration":75,
            "schedule":"10:00 AM",
            "listing_id":"10",
            "logo_name":null,
            "ratings":5,
            "price":{  
               "activity_price":95,
               "transportation_price":0,
               "multimedia_price":18
            },
            "listing_type":"Tandem",
            "location":{  
               "geo_location":{  
                  "lon":0,
                  "lat":0
               }
            },
            "company":{  
               "name":"Flying Munky Airventures"
            },
            "ranking":[  
               1
            ],
            "title":"Flying Munky Airventures"
         }
      },
      {  
         "result":{  
            "duration":30,
            "schedule":null,
            "listing_id":"6",
            "logo_name":"70726f64756374696f6e/a87ff679a2f3e71d9181a67b7542122c/8889da95-1ecb-4227-98c6-52561ba2e571.jpg",
            "ratings":0,
            "price":{  
               "activity_price":85,
               "transportation_price":10,
               "multimedia_price":17
            },
            "listing_type":"Tandem",
            "location":{  
               "geo_location":{  
                  "lon":0,
                  "lat":0
               }
            },
            "company":{  
               "name":"Paragliding in Godawari"
            },
            "ranking":[  
               2
            ],
            "title":"Paragliding in Godawari"
         }
      },
      {  
         "result":{  
            "duration":30,
            "schedule":null,
            "listing_id":"6",
            "logo_name":"70726f64756374696f6e/a87ff679a2f3e71d9181a67b7542122c/8889da95-1ecb-4227-98c6-52561ba2e571.jpg",
            "ratings":0,
            "price":{  
               "activity_price":95,
               "transportation_price":10,
               "multimedia_price":17
            },
            "listing_type":"Solo",
            "location":{  
               "geo_location":{  
                  "lon":0,
                  "lat":0
               }
            },
            "company":{  
               "name":"Paragliding in Godawari"
            },
            "ranking":[  
               3
            ],
            "title":"Paragliding in Godawari"
         }
      }
   ]
}
```

### For Data Transfer from MySql to Elasticsearch: ###
 Request URL:- http://<servername>:<serverport>/GoGlidesSearchEngine/goglides/dataoperation

### Request JSON: ###

**For Delete**

```
#!json

{
    "data_operation": {
        "id": 3,
        "dbOperation": "DELETE"	
    }
}

```

**For Insert**

```
#!json

{  
   "data_operation":{  
      "id":6,
      "dbOperation":"INSERT",
      "datas":[  
         {  
            "code":"KTMP",
            "unavailable_date":null,
            "listing_id":"6",
            "logo_name":"70726f64756374696f6e/a87ff679a2f3e71d9181a67b7542122c/8889da95-1ecb-4227-98c6-52561ba2e571.jpg",
            "age_group":null,
            "description":"<p>We create experiences that&nbsp;makes you smile, increase your heart beat and we will give you life time experience and memories with still and motion pictures.<\/p>\r\n",
            "listing_type":"Tandem",
            "title":"Paragliding in Godawari",
            "duration":30,
            "schedule":null,
            "feature":{  
               "featured_end":null,
               "featured_start":null,
               "is_featured":null
            },
            "ratings":0,
            "price":{  
               "activity_price":85,
               "transportation_price":10,
               "multimedia_price":17
            },
            "location":{  
               "country":null,
               "address":"Godawari, Lalitpur, Nepal",
               "geo_location":{  
                  "lon":0,
                  "lat":0
               },
               "city":null,
               "state":null
            },
            "company":{  
               "name":"Paragliding in Godawari",
               "location":{  
                  "country":null,
                  "address":"Godawari, Lalitpur, Nepal",
                  "geo_location":{  
                     "lon":0,
                     "lat":0
                  },
                  "city":null,
                  "state":null
               }
            },
            "category":"Paragliding",
            "slug":"paragliding-in-godawari-o5j",
            "status":"publish"
         }
      ]
   }
}

```

### Response JSON: ###

```
#!json

{
  "failure": false,
  "successful": true
}
```

### SQL Used to Fetch Data from MySQL: ###


```
#!sql

SELECT DISTINCT a.id                AS listing_id, 
                a.title             AS title, 
                a.address           AS listing_address, 
                a.city              AS listing_city, 
                a.country           AS listing_country, 
                a.state             AS listing_state, 
                a.lat               AS listing_latitude, 
                a.lon               AS listing_longitude, 
                a.description       AS listing_description, 
                a.featured_end      AS listing_featured_end, 
                a.featured_start    AS listing_featured_start, 
                a.is_featured       AS listing_is_featured, 
                a.listing_code      AS listing_code, 
                a.slug              AS listing_slug, 
                a.status            AS listing_status, 
                a.address           AS company_address, 
                a.city              AS company_city, 
                a.country           AS company_country, 
                a.state             AS company_state, 
				b.geolocation_lat   AS listing_latitude, 
                b.geolocation_long  AS listing_longitude, 
				b.business_name     AS company_name,
				c.rating            AS ratings, 
                d.category_title    AS listing_category, 
                e.schedule          AS schedule, 
                f.type              AS listing_type, 
                f.age_group         AS age_group, 
                f.amount            AS activity_price, 
                f.multimedia_charge AS multimedia_price, 
                f.transportation    AS transportation_price, 
                f.duration          AS duration, 
                g.date              AS unavailable_date,
				h.logo              AS listing_logo				
FROM   listing a 
       LEFT JOIN business_directory b 
               ON a.business_directory_id = b.id 
       LEFT JOIN (SELECT listing_id, 
                         Avg(rating) AS rating 
                  FROM   listing_review 
                  GROUP  BY listing_id) c 
              ON a.id = c.listing_id 
       LEFT JOIN (SELECT d.category_title AS category_title, 
                          g.listing_id     AS listing_id 
                   FROM   category d 
                          INNER JOIN listing_category g 
                                  ON d.id = g.category_id) d 
               ON a.id = d.listing_id 
       LEFT JOIN listing_schedule e 
              ON a.id = e.listing_id 
       LEFT JOIN (SELECT listing_id, 
                         type, 
                         age_group, 
                         amount, 
                         multimedia_charge, 
                         transportation, 
                         duration 
                  FROM   listing_price 
                  WHERE  nationality = 'Foreign')f 
              ON a.id = f.listing_id 
       LEFT JOIN (SELECT listing_id, 
                         Group_concat(DISTINCT date ORDER BY date ASC SEPARATOR 
                         ',') date 
                  FROM   listing_calender 
                  GROUP  BY listing_id) g 
              ON a.id = g.listing_id 
	   LEFT JOIN (SELECT listing_id, 
                         file as logo
                  FROM listing_multimedia where type = '_feature')h 
              ON a.id = h.listing_id 
WHERE  a.id = ? 
ORDER  BY a.id 
```