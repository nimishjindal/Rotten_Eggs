from nltk.classify import ClassifierI
from statistics import mode 
import pickle
from nltk.tokenize import word_tokenize
import os
import sys

Path = "C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Classifiers"
FileList = []
for filename in os.listdir(Path):
    FileList.append(Path+"/"+filename)

#print(FileList)
    
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/BOW.pickle",'rb') as s:
    BOW=pickle.load(s)   

def find_features(sent):
   
    words=word_tokenize(sent)
    features={}
    for w in BOW:
       features[w]=(w in words )
       
    return features


class VoteClassifier(ClassifierI):
    
    def classify(self,features):
        votes=[]
        for file in FileList:
            with open(file,'rb') as s:
                c=pickle.load(s)
                v=c.classify(features)
                votes.append(v)
        
        return mode(votes)
    
    def classifyAndConf(self,features):
        votes=[]
        for file in FileList:
            with open(file,'rb') as s:
                c=pickle.load(s)
                v=c.classify(features)
                votes.append(v)
        
        choice_vote=votes.count(mode(votes))
        conf = choice_vote / len(votes)
        return conf,mode(votes)

votedClassifier= VoteClassifier()

query = sys.argv[1]
Text= find_features(query)
conf,vote = votedClassifier.classifyAndConf(Text)

print(conf*100,vote)
