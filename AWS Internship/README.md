https://www.python.org/downloads/
pip3 install deap
pip3 install numpy
pip3 install pandas
pip3 install boto3
pip3 install pyinstaller
pyinstaller --onefile your_script.py

/Applications/Python\ 3.11/Install\ Certificates.command

# If you want to make the file an executable on unix you would need to run this command 'chmod +x filename' replace filename with the name of your file

aws s3 cp /home/ec2-user/dist/mealplan s3://project444/
