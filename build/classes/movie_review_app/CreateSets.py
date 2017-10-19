from nltk.tokenize import word_tokenize
import random
import pickle
from collections import Counter
from nltk.stem import WordNetLemmatizer
from pathlib import Path

lemmatizer = WordNetLemmatizer()
table=[]
total = 0
movieName = "Interstellar"


def incTotal():
    global total
    total+=1
    
def createLexicon():

    global movieName
    lexicon = []
    L=[]
    with open(r'C:\rtPolarity.neg') as file:
        for row in file:
            incTotal()
            table.append((row,'Negative'))
            words = word_tokenize(row)
            L += list(words)
            
    with open(r'C:\rtPolarity.pos') as file:
        for row in file:
            incTotal()
            table.append((row,'Positive'))
            words = word_tokenize(row)
            L += list(words)

    p = "C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/DataSets/NewReviews/"+movieName    
    

    myFile = Path(p+"\\review.pos")
    if myFile.is_file():
        with open(p+"\\review.pos",'r') as file:
            for row in file:
                incTotal()
                table.append((row,'Positive'))
                words = word_tokenize(row)
                L += list(words)

    myFile = Path(p+"\\review.neg")
    if myFile.is_file():
        with open(p+"\\review.neg",'r') as file:
            for row in file:
                incTotal()
                table.append((row,'Negative'))
                words = word_tokenize(row)
                L += list(words)

          
    L = [lemmatizer.lemmatize(i).lower() for i in L]
    w_counts = Counter(L)

    for w in w_counts:
        if 1000 > w_counts[w] > 50:
            lexicon.append(w)
    
    lexicon.append('not')
    
    return lexicon

BOW = createLexicon()

def find_features(sent):
   
    words=word_tokenize(sent)
    features={}
    for w in BOW:
       features[w]=(w in words )
       
    return features

def createFeatureSet():
    featureSet = [(find_features(sent),category) for (sent,category) in table]
    random.shuffle(featureSet)
    return featureSet

def createSets(trainPercent=0.9):
    
    global total
    trainSize = int(trainPercent*total)  #90% * total
    
    print(total)
    
    featureSet=createFeatureSet()
    
    train = featureSet[:trainSize]
    test = featureSet[trainSize:]
    
    return train,test 

train,test = createSets()    
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/Train.pickle",'wb') as s:
    pickle.dump(train,s)   
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/Test.pickle",'wb') as s:
    pickle.dump(test,s) 
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/BOW.pickle",'wb') as s:
    pickle.dump(BOW,s) 




