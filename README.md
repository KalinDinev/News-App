# News-App
The user can select news on different topics. We will be getting the news as JSON from
external API. Also, I will use SharedPreferences to save the number of read articles

● News Topics Fragment -> This is the first Fragment where the user can select a
topic of news
● Articles Fragment -> After the user taps a topic, we will fetch the news/articles
about it. It's showing only the article's title and image.
● Article Details Fragment - show the whole info for the article.

The external API: https://newsapi.org


 Functionalities:

 ● It's calling external API to get the news JSON using OkHttp.
 
 ● After the article is open, increase the number of read articles in
SharedPreferences. The number of read news it's  shown at the top of
Article Topics Fragment

● The whole information about each article from the JSON response is shown in
Articles Details Fragment. Also, the image from the "urlToImage" property is
 loaded. A button with  title "Open in web" in Articles Details
Fragment which will open the "URL" property from the JSON object in the current browser.
