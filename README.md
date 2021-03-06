# Rotten Eggs: Movie review App

Rotten Eggs is an app which uses Artificial Intelligence to make categorizing movie reviews easier and effortless. This software makes calculating, rating and categorizing reviews automated perhaps reducing labor. This application collects movie reviews by the users (classified) which makes classifiers smarter and more powerful with time.

## About The modules

Navigate to `src\movie_review_app\` for the main files

* `Classifiers/`- contains Trained classifiers as pickles
* `DataSets/` - Contains Positive and negative reivews for training the classifiers
* `Pickles/` - contains reviews formated according to the classifiers 
* `Posters/` - contains posters of movies (currently just 1)
* `AddNewReview.java`- Java thread to add a new review to the data
* `Moview_review_app.java` -The main file
* `ReTrain.java` - Thread to retrain th eclassifiers with new data
* `runPython.java` - Run the classifiers (Coded in python) using cmd args
* `CreateSets.py` - format raw reviews according to the format required by the classifiers. Also Creates Bag Of Words.
* `TrainClassifiers.py` - Does what is says
* `Classify.py` - classifies revies as negative or positive and returns the result
 
## working


### UI
<img src="Screenshots/1.jpg" alt="alt text" width="500" height="350">

This is the interface of 'Rotten Eggs'. The front-end application is java based. The user can see the name of the movie, it's poster and can add reviews in the text box. All previous reviews of the movie can be viewed below the text box.

### Reviews

<img src="Screenshots/2.jpg" alt="alt text" width="500" height="350">
 
I added a review and clicked on submit. The classifier, which is based python working at the back-end classified the review as a positive one. The application gives an option to the user to correct the prediction if it's wrong.

<img src="Screenshots/3.jpg" alt="alt text" width="500" height="350">
 
Since, I added a positive review and the classifier predicted it correctly , I clicked on 'Yes' and the review was added in the reviews section. The classification is shown adjacent to the review. The app also calculates rating based on all reviews.
 
<img src="Screenshots/4.jpg" alt="alt text" width="500" height="350">
 
To test, I also added a negative review which was predicted correctly and was added to the review section which became the second review. Now, I added a new review and clicked on submit but this time it predicted it wrong. So, I have provided with a feature to correct this prediction manually by pressing on 'No'. The review will be added to the review section with the corrected classification as shown below.
 
<img src="Screenshots/5.jpg" alt="alt text" width="500" height="350">

At this point, the classifier is retrained taking input of the new reviews/data that was entered. Hence, the application becomes more intelligent every time it's being used.
