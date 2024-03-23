# A Setup file to create the database and tables

from colorama import Fore
import time
import mysql.connector as mys

def createDB():
    
    try:
        conn = mys.connect(host="localhost", user = "root", password = "")
        cursor = conn.cursor()
        cursor.execute("CREATE DATABASE IF NOT EXISTS javadb")
        cursor.execute("USE javadb")
        cursor.execute("CREATE TABLE IF NOT EXISTS users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))")
        cursor.execute("Insert into users values('admin', '12345')")
        cursor.execute("CREATE TABLE IF NOT EXISTS students (MIS INT PRIMARY KEY, Name VARCHAR(200), Batch VARCHAR(50), Phone varchar(50), Email VARCHAR(50))")
        conn.commit()
        conn.close()
        
        print (Fore.GREEN + "Database and Tables created successfully" + Fore.RESET)
        time.sleep(2)
    
    except Exception as e:
        print (Fore.RED + "Error in creating Database and Tables" + Fore.RESET)
        print (Fore.RED + str(e) + Fore.RESET)
        time.sleep(2)
        
        print(Fore.CYAN + "\nMake sure that your DB is operable, and you have updated the 'setup.py' file as well as in the Java program." + Fore.RESET)
        time.sleep(2)
        print(Fore.CYAN + "\nExiting the program..." + Fore.RESET)
        time.sleep(2)
        exit()


if __name__ == "__main__":
    createDB()
    
    