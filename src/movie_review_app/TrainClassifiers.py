import nltk
from nltk.tokenize import word_tokenize
import pickle
from nltk.classify.scikitlearn import SklearnClassifier
from sklearn.naive_bayes import MultinomialNB, BernoulliNB
from sklearn.svm import LinearSVC,NuSVC

with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/Train.pickle",'rb') as s:
     train=pickle.load(s)    
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/Test.pickle",'rb') as s:
     test=pickle.load(s)   
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Pickles/BOW.pickle",'rb') as s:
     BOW=pickle.load(s)    



def find_features(sent):
   
    words=word_tokenize(sent)
    features={}
    for w in BOW:
       features[w]=(w in words )
       
    return features

train += test

NBclassifier = nltk.NaiveBayesClassifier.train(train)
#print("orginal NB accuracy",(nltk.classify.accuracy(NBclassifier,test))*100)

MNBclassifier = SklearnClassifier(MultinomialNB())
MNBclassifier.train(train)
#print("classifier accuracy",(nltk.classify.accuracy(MNBclassifier,test))*100)

LinearSVC_classifier = SklearnClassifier(LinearSVC())
LinearSVC_classifier.train(train)
#print("LinearSVC_classifier accuracy percent:", (nltk.classify.accuracy(LinearSVC_classifier, test))*100)

NuSVC_classifier = SklearnClassifier(NuSVC())
NuSVC_classifier.train(train)
#print("NuSVC_classifier accuracy percent:", (nltk.classify.accuracy(NuSVC_classifier, test))*100)

BernoulliNB_classifier = SklearnClassifier(BernoulliNB())
BernoulliNB_classifier.train(train)
#print("BernoulliNB_classifier accuracy",(nltk.classify.accuracy(BernoulliNB_classifier,test))*100

print("Trained")
with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Classifiers/NBC.pickle",'wb') as s:
    pickle.dump(NBclassifier,s)

with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Classifiers/MNBC.pickle",'wb') as s:
    pickle.dump(MNBclassifier,s)

with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Classifiers/LSVC.pickle",'wb') as s:
    pickle.dump(LinearSVC_classifier,s)

with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Classifiers/NSVC.pickle",'wb') as s:
    pickle.dump(NuSVC_classifier,s)

with open("C:/Users/Sanjay Jindal/Documents/movie_review_app/src/movie_review_app/Classifiers/BNBC.pickle",'wb') as s:
    pickle.dump(BernoulliNB_classifier,s)

print("Saved CLassifiers")
