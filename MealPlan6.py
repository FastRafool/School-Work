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
explanation = tk.Label(root, text="Welcome! This program generates weekly meal plans based on your calorie target and/or item count.")
explanation.pack()

# Load the dataset
data = pd.read_csv('https://project444.s3.amazonaws.com/ProjectDataset.csv')

# Define the problem type: Prioritizing protein intake and minimizing price
creator.create("FitnessMax", base.Fitness, weights=(1, 1e6, 100, -1.0, -1.0, -1.0, -1.0))
creator.create("Individual", list, fitness=creator.FitnessMax, serving_dict=None)

# Initialize a toolbox
toolbox = base.Toolbox()

# Define the decision variable: Index of food item in the dataset
toolbox.register("index", random.randint, 0, len(data) - 1)

# Let's compute the average calorie count of the food items in the dataset
average_calorie_count = data["Calories"].mean()

# Define your function
def determine_individual_size(calorie_target):
    # Based on the average calorie count of the food items, calculate how many items would be needed
    # to approximately match the target calorie count, and round it to the nearest integer.
    # We limit the size between 2 and 21 as specified.
    individual_size = max(2, min(21, round(calorie_target / average_calorie_count)))
    return individual_size

# Define a default value for INDIVIDUAL_SIZE
INDIVIDUAL_SIZE = 20

# Ask the user for a calorie target
def ask_calorie_target():
    try:
       
        # Also ask for the daily calorie target here
        calorie_target = simpledialog.askinteger("Input", "How many calories do you want to consume per day?", parent=root, minvalue=1, maxvalue=1e6)
        global DAILY_CALORIE_TARGET
        DAILY_CALORIE_TARGET = calorie_target
        messagebox.showinfo("Info", f"Calorie target set to {calorie_target} per day.")
        global INDIVIDUAL_SIZE  # We also need to determine the individual size here
        INDIVIDUAL_SIZE = determine_individual_size(DAILY_CALORIE_TARGET)

        # Register individual and population with the determined size
        toolbox.register("individual", tools.initRepeat, creator.Individual, toolbox.index, n=INDIVIDUAL_SIZE)
        toolbox.register("population", tools.initRepeat, list, toolbox.individual)

        run_algorithm()  # Call the algorithm function here
    except Exception as e:
        messagebox.showerror("Error", str(e))

# Ask the user for the number of items in the meal plan and for the calorie target
def ask_item_count():
    try:
        item_count = simpledialog.askinteger("Input", "How many items do you want in your meal plan?", parent=root, minvalue=2, maxvalue=1e6)
        global INDIVIDUAL_SIZE  # We need to declare the variable as global so we can modify it
        INDIVIDUAL_SIZE = item_count
        messagebox.showinfo("Info", f"Meal plan will include {item_count} items.")

        # Also ask for the daily calorie target here
        calorie_target = simpledialog.askinteger("Input", "How many calories do you want to consume per day?", parent=root, minvalue=1, maxvalue=1e6)
        global DAILY_CALORIE_TARGET
        DAILY_CALORIE_TARGET = calorie_target
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

    # Compute the difference between the total calories and the target
    calorie_difference = abs(total_calories - DAILY_CALORIE_TARGET * 7)
    
    # Return the objectives. Note that NSGA-II is a maximization algorithm, so for calorie_difference
    # we return the negative value to make the algorithm try to minimize it.

    return total_protein, -calorie_difference, total_calories, total_fat, total_carbs, total_sodium, total_price

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
        mealplan_count = simpledialog.askinteger("Input", "How many meal plans do you want to generate?", parent=root, minvalue=1, maxvalue=1e6)
        global MEALPLAN_COUNT
        MEALPLAN_COUNT = mealplan_count
        messagebox.showinfo("Info", f"{mealplan_count} meal plans will be generated.")
    except Exception as e:
        messagebox.showerror("Error", str(e))

def run_algorithm():
    # Generate the initial population
    pop = toolbox.population(n=200)

    # Perform the NSGA-II algorithm for a number of generations
    algorithms.eaSimple(pop, toolbox, cxpb=0.5, mutpb=0.2, ngen=1000, verbose=False)

    # Extract the best individuals (food combinations) from the final population
    best_individuals = tools.selBest(pop, k=MEALPLAN_COUNT)

    # Sort the best individuals by the absolute difference between their total calories and the user's daily calorie target
    best_individuals.sort(key=lambda individual: abs(evaluate(individual)[2] - DAILY_CALORIE_TARGET * 7))

    results = []
    for i, individual in enumerate(best_individuals):
        result = f"Meal plan {i+1}:\n"
        total_protein, calorie_difference, total_calories, total_fat, total_carbs, total_sodium, total_price = evaluate(individual)
        servings_dict = individual.servings_dict
        # Count the occurrences of each asin in the individual
        asin_counts = Counter(individual)
        
        for asin, count in asin_counts.items():
            # Use slashes to separate the values and format servings to 2 decimal places
            result += f"{data.loc[asin, 'asin']} x{count} / Daily servings: {servings_dict[asin]*count:.2f} / {data.loc[asin, 'Name']}\n"
        
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
button3 = tk.Button(root, text="Set item count, calorie target and run algorithm", command=ask_item_count)
button3.pack()

root.mainloop()
