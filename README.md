# BasketballPlayers
MVVM and retrofit

This simple project displays the information of players from the server;

The code is built on MVVM design patter and retrofit librar for web service .

Data is fetched by retrofit library using API : http://www.balldontlie.io/api/v1/
its tack two parameter : 
1. page  = The page number, used for pagination.
2. per-page = The number of results returned per call, used for pagination. Max 100

Data shown in recyclerview 
The app have one activity call Main activity which displays data through recyclerview 
PlayerAdapter for utilize multi item recyclerview , adapter have two item :
1. row_players : That displays player information 
2. row_loading_item :  load indicator, Added at the end of the list and deleted when user move to the next page

![APP](https://github.com/AbdallahMustafaQasem/BasketballPlayers/blob/master/app/src/main/res/drawable-v24/capture.PNG?raw=true)
