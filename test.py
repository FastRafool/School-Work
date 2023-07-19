import getpass
import random
import numpy as np
import pandas as pd
from deap import base, creator, tools, algorithms
from io import StringIO
import tkinter as tk
from tkinter import messagebox, simpledialog
from collections import Counter
import boto3

# Create a Tkinter root window
root = tk.Tk()

# Add a Label with a brief explanation of the program
explanation = tk.Label(root, text="This program generates a meal plan for a week based on your calorie target.")
explanation.pack()

# Load the dataset
data = pd.read_csv('https://project444.s3.amazonaws.com/ProjectDataset.csv')

# Define the problem type: Prioritizing protein intake and minimizing price
creator.create("FitnessMax", base.Fitness, weights=(1, 100, -1.0, -1.0, -1.0, -1.0))
creator.create("Individual", list, fitness=creator.FitnessMax, serving_dict=None)

# Initialize a toolbox
toolbox = base.Toolbox()

# Define the decision variable: Index of food item in the dataset
toolbox.register("index", random.randint, 0, len(data) - 1)

# Define your function
def determine_individual_size(calorie_target):
    if calorie_target < 900:
        return 16
    elif 900 <= calorie_target < 1300:
        return 2
    elif 1300 <= calorie_target < 1700:
        return 18
    elif 1700 <= calorie_target < 2100:
        return 19
    elif 2100 <= calorie_target < 2500:
        return 20
    else:  # calorie_target >= 2500
        return 21

# Define a default value for INDIVIDUAL_SIZE
INDIVIDUAL_SIZE = 20

# Ask the user for a calorie target
def ask_calorie_target():
    try:
        calorie_target = simpledialog.askinteger("Input", "How many calories do you want to consume per day?", parent=root, minvalue=1, maxvalue=5000)
        global DAILY_CALORIE_TARGET  # We need to declare the variable as global so we can modify it
        DAILY_CALORIE_TARGET = calorie_target
        global INDIVIDUAL_SIZE  # We also need to determine the individual size here
        INDIVIDUAL_SIZE = determine_individual_size(DAILY_CALORIE_TARGET)
        messagebox.showinfo("Info", f"Calorie target set to {calorie_target} per day.")
        
        # Register individual and population with the determined size
        toolbox.register("individual", tools.initRepeat, creator.Individual, toolbox.index, n=INDIVIDUAL_SIZE)
        toolbox.register("population", tools.initRepeat, list, toolbox.individual)

        run_algorithm()  # Call the algorithm function here
    except Exception as e:
        messagebox.showerror("Error", str(e))

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

def ask_mealplan_count():
    try:
        mealplan_count = simpledialog.askinteger("Input", "How many meal plans do you want to generate?", parent=root, minvalue=1, maxvalue=50)
        global MEALPLAN_COUNT
        MEALPLAN_COUNT = mealplan_count
        messagebox.showinfo("Info", f"{mealplan_count} meal plans will be generated.")
    except Exception as e:
        messagebox.showerror("Error", str(e))

def run_algorithm():
    # Generate the initial population
    pop = toolbox.population(n=100)

    # Perform the NSGA-II algorithm for a number of generations
    algorithms.eaSimple(pop, toolbox, cxpb=0.5, mutpb=0.2, ngen=500, verbose=False)

    # Extract the best individuals (food combinations) from the final population
    best_individuals = tools.selBest(pop, k=MEALPLAN_COUNT)

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

button2 = tk.Button(root, text="Set mealplan count", command=ask_mealplan_count)
button2.pack()
button = tk.Button(root, text="Set calorie target and run algorithm", command=ask_calorie_target)
button.pack()
root.mainloop()
