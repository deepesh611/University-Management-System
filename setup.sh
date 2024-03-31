#!/bin/bash

# Check if pip is installed
if ! [ -x "$(command -v pip)" ]; then
  echo -e "\033[0;31mError:\033[0m \033[1;31mpip is not installed. Please install pip before running this script.\033[0m" >&2
  exit 1
fi

# Install the required packages
echo -e "\n\033[0;34mInstalling the required packages...\033[0m"
pip install -r ./src/requirements.txt
if [ $? -ne 0 ]; then
  echo -e "\033[0;31mError:\033[0m \033[1;31mFailed to install packages from requirements.txt\033[0m"
  exit 1
fi
echo -e "\n\033[0;32mPackages installed\n\033[0m"

sleep 1

# Run the SetupDB file
echo -e "\033[0;34mSetting up your database...\033[0m"
python ./src/SetupDB.py
