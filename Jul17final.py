import getpass
import random
import numpy as np
import pandas as pd
from deap import base, creator, tools, algorithms
from io import StringIO
import tkinter as tk
from tkinter import messagebox
from collections import Counter
import boto3

# Create a Tkinter root window
root = tk.Tk()

# Add a Label with a brief explanation of the program
explanation = tk.Label(root, text="This program generates a meal plan for a week based on your calorie target.")
explanation.pack()


# Load the dataset
data = pd.read_csv('https://project444.s3.amazonaws.com/ProjectDataset.csv')

# Constants
DAILY_CALORIE_TARGET = 2500
CALORIE_TARGET = DAILY_CALORIE_TARGET * 7  # for a week
PROTEIN_TARGET = 150 * 7  # 1g per pound of body weight for a week
FAT_TARGET = 60 * 7  # 0.4g per pound of body weight for a week

# Define the problem type: Prioritizing protein intake and minimizing price
creator.create("FitnessMax", base.Fitness, weights=(1, 100, -1.0, -1.0, -1.0, -1.0))
creator.create("Individual", list, fitness=creator.FitnessMax, serving_dict=None)

# Initialize a toolbox
toolbox = base.Toolbox()

# Define the decision variable: Index of food item in the dataset
toolbox.register("index", random.randint, 0, len(data) - 1)

# Define an individual (solution) as a list of food item indices
toolbox.register("individual", tools.initRepeat, creator.Individual, toolbox.index, n=21)

# Define the population as a list of individuals
toolbox.register("population", tools.initRepeat, list, toolbox.individual)

# Define the evaluation function
def evaluate(individual):
    total_calories = 0
    total_protein = 0
    total_fat = 0
    total_carbs = 0
    total_sodium = 0
    total_price = 0
    servings_dict = {}  # Dictionary to track daily servings for each food item
    for index in individual:
        servings = data.loc[index, "Total Serving"]
        total_calories += data.loc[index, "Calories"] * servings
        total_protein += data.loc[index, "Protein (g)"] * servings
        total_fat += data.loc[index, "Total Fat (g)"] * servings
        total_carbs += data.loc[index, "Total Carbohydrate (g)"] * servings
        total_sodium += data.loc[index, "Sodium (mg)"] * servings
        total_price += data.loc[index, "Price ($)"]
        servings_dict[index] = servings / 7  # Calculate daily servings for each food item
    individual.servings_dict = servings_dict  # Store servings_dict in the individual
    return total_protein, total_calories, total_fat, total_carbs, total_sodium, total_price

# Define the evaluation operator
toolbox.register("evaluate", evaluate)

# Define the selection operator
toolbox.register("select", tools.selNSGA2)

# Define the crossover operator
toolbox.register("mate", tools.cxTwoPoint)

# Define the mutation operator
toolbox.register("mutate", tools.mutUniformInt, low=0, up=len(data) - 1, indpb=0.1)

# Set the random seed for reproducibility
random.seed(1)

# Generate the initial population
pop = toolbox.population(n=100)

# Perform the NSGA-II algorithm for a number of generations
algorithms.eaSimple(pop, toolbox, cxpb=0.5, mutpb=0.2, ngen=500, verbose=False)

# Extract the best individuals (food combinations) from the final population
best_individuals = tools.selBest(pop, k=10)

from collections import Counter

def run_algorithm():
    results = []
    for i, individual in enumerate(best_individuals):
        result = f"Meal plan {i+1}:\n"
        total_protein, total_calories, total_fat, total_carbs, total_sodium, total_price = evaluate(individual)
        servings_dict = individual.servings_dict
        # Count the occurrences of each asin in the individual
        asin_counts = Counter(individual)
        
        for asin, count in asin_counts.items():
            # Use slashes to separate the values and format servings to 2 decimal places
            result += f"{data.loc[asin, 'asin']} x{count} / Daily servings: {servings_dict[asin]*count:.2f}\n"
        
        result += f"Average daily protein: {total_protein / 7:.2f}\n"
        result += f"Average daily calories: {total_calories / 7:.2f}\n"
        result += f"Average daily fat: {total_fat / 7:.2f}\n"
        result += f"Average daily sodium: {total_sodium / 7:.2f}\n"
        result += f"Average daily carbohydrates: {total_carbs / 7:.2f}\n"
        result += f"Total price: {total_price:.2f}\n"
        results.append(result)
    
    # Create a new window
    result_window = tk.Toplevel(root)
    result_window.title("Results")
    
    # Add a scrollbar
    scrollbar = tk.Scrollbar(result_window)
    scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
    
    # Add a Text widget with a scrollbar
    text = tk.Text(result_window, wrap=tk.WORD, yscrollcommand=scrollbar.set)
    text.insert(tk.END, "\n".join(results))
    text.pack()
    
    # Configure the scrollbar
    scrollbar.config(command=text.yview)





root = tk.Tk()
button = tk.Button(root, text="Run algorithm", command=run_algorithm)
button.pack()
root.mainloop()
