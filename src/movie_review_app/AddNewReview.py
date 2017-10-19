#import os
import sys



sent = sys.argv[1]+"\n"
category = sys.argv[2]
movieName = sys.argv[3]

p = "C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/DataSets/NewReviews/"+movieName

FileName = "review."+category
#
#print(FileName)


f = open(p+"/"+FileName,'a')
f.write(sent)
f.close()

print("added review",sent," type ",category)
