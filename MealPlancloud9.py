import random
import numpy as np
import pandas as pd
from deap import base, creator, tools, algorithms
from collections import Counter

# Load the dataset
data = pd.read_csv('https://project444.s3.amazonaws.com/ProjectDataset.csv')

# Define the problem type: Prioritizing protein intake and minimizing price
creator.create("FitnessMax", base.Fitness, weights=(1, 1e6, 100, -1.0, -1.0, -1.0, -1.0))
creator.create("Individual", list, fitness=creator.FitnessMax, serving_dict=None)

# Initialize a toolbox
toolbox = base.Toolbox()

# Define the decision variable: Index of food item in the dataset
toolbox.register("index", random.randint, 0, len(data) - 1)

# Compute the average calorie count of the food items in the dataset
average_calorie_count = data["Calories"].mean()

# Define a function to determine individual size based on a calorie target
def determine_individual_size(calorie_target):
    individual_size = max(2, min(21, round(calorie_target / average_calorie_count)))
    return individual_size

# Define a default value for INDIVIDUAL_SIZE
INDIVIDUAL_SIZE = 20

# Ask the user for a calorie target
def ask_calorie_target():
    try:
        # Ask for the daily calorie target
        calorie_target = int(input("How many calories do you want to consume per day? "))
        global DAILY_CALORIE_TARGET
        DAILY_CALORIE_TARGET = calorie_target
        print(f"Calorie target set to {calorie_target} per day.")
        global INDIVIDUAL_SIZE
        INDIVIDUAL_SIZE = determine_individual_size(DAILY_CALORIE_TARGET)

        # Register individual and population with the determined size
        toolbox.register("individual", tools.initRepeat, creator.Individual, toolbox.index, n=INDIVIDUAL_SIZE)
        toolbox.register("population", tools.initRepeat, list, toolbox.individual)

        run_algorithm()  # Call the algorithm function
    except Exception as e:
        print("Error:", e)

# Ask the user for the number of items in the meal plan and for the calorie target
def ask_item_count():
    try:
        item_count = int(input("How many items do you want in your meal plan? "))
        global INDIVIDUAL_SIZE
        INDIVIDUAL_SIZE = item_count
        print(f"Meal plan will include {item_count} items.")

        # Also ask for the daily calorie target
        calorie_target = int(input("How many calories do you want to consume per day? "))
        global DAILY_CALORIE_TARGET
        DAILY_CALORIE_TARGET = calorie_target
        print(f"Calorie target set to {calorie_target} per day.")
        
        # Register individual and population with the determined size
        toolbox.register("individual", tools.initRepeat, creator.Individual, toolbox.index, n=INDIVIDUAL_SIZE)
        toolbox.register("population", tools.initRepeat, list, toolbox.individual)

        run_algorithm()  # Call the algorithm function
    except Exception as e:
        print("Error:", e)

# Define the evaluation function
def evaluate(individual):
    total_calories = 0
    total_protein = 0
    total_fat = 0
    total_carbs = 0
    total_sodium = 0
    total_price = 0
    servings_dict = {}
    for index in individual:
        servings = data.loc[index, "Total Serving"]
        total_calories += data.loc[index, "Calories"] * servings
        total_protein += data.loc[index, "Protein (g)"] * servings
        total_fat += data.loc[index, "Total Fat (g)"] * servings
        total_carbs += data.loc[index, "Total Carbohydrate (g)"] * servings
        total_sodium += data.loc[index, "Sodium (mg)"] * servings
        total_price += data.loc[index, "Price ($)"]
        servings_dict[index] = servings / 7
    individual.servings_dict = servings_dict

    # Compute the difference between the total calories and the target
    calorie_difference = abs(total_calories - DAILY_CALORIE_TARGET * 7)

    return total_protein, -calorie_difference, total_calories, total_fat, total_carbs, total_sodium, total_price

# Register the operators
toolbox.register("evaluate", evaluate)
toolbox.register("select", tools.selNSGA2)
toolbox.register("mate", tools.cxTwoPoint)
toolbox.register("mutate", tools.mutUniformInt, low=0, up=len(data) - 1, indpb=0.1)

# Set the random seed for reproducibility
random.seed(1)

# Ask the user for the meal plan count
def ask_mealplan_count():
    try:
        mealplan_count = int(input("How many meal plans do you want to generate? "))
        global MEALPLAN_COUNT
        MEALPLAN_COUNT = mealplan_count
        print(f"{mealplan_count} meal plans will be generated.")
    except Exception as e:
        print("Error:", e)

# Define the main algorithm
def run_algorithm():
    pop = toolbox.population(n=200)
    algorithms.eaSimple(pop, toolbox, cxpb=0.5, mutpb=0.2, ngen=1000, verbose=False)

    best_individuals = tools.selBest(pop, k=MEALPLAN_COUNT)
    best_individuals.sort(key=lambda individual: abs(evaluate(individual)[2] - DAILY_CALORIE_TARGET * 7))

    for i, individual in enumerate(best_individuals):
        print(f"\nMeal plan {i+1}:")
        total_protein, calorie_difference, total_calories, total_fat, total_carbs, total_sodium, total_price = evaluate(individual)
        servings_dict = individual.servings_dict
        asin_counts = Counter(individual)
        
        for asin, count in asin_counts.items():
            print(f"{data.loc[asin, 'asin']} x{count} / Daily servings: {servings_dict[asin]*count:.2f} / {data.loc[asin, 'Name']}")
        
        print(f"Average daily protein: {total_protein / 7:.2f}g")
        print(f"Average daily calories: {total_calories / 7:.2f}cal")
        print(f"Average daily fat: {total_fat / 7:.2f}g")
        print(f"Average daily sodium: {total_sodium / 7:.2f}mg")
        print(f"Average daily carbohydrates: {total_carbs / 7:.2f}g")
        print(f"Total price: ${total_price:.2f}")

# Ask the user to set the meal plan count
ask_mealplan_count()
# Ask the user to set the calorie target and run the algorithm
ask_calorie_target()
