-za KNNClassification(i za KNN imputation) se koristat podatoci skalirani so MinMaxScaler(numericki podatoci). Skaliranjeto se praj na celo X

-za regresija so linearna, za Logistic Regression: Se skaliraat numerickite podatoci so StandardScaler. Za X scalerot se trenira so X_train i se transformiraat i X_train i X_test,
a za Y, se trenira so Y_train i se transformira istiot. Pri presmetka na performansi, se pravi inverzija na vrednostite od Y_pred.

-Za DecisionTreeClassifier, DecisionTreeRegressor i RandomForestRegressor nema skaliranje