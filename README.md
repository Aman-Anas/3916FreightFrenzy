# FTC Team 3916 - Apex Robotics
This is our repo for the 2020-2021 game year - Ultimate Goal

## Installation
Clone this repo. You can do this through the web interface (download a .zip) or through git (use the command listed).
Locate the project. The default location for GitHub clones is C:\Users\<your user name>\Documents\GitHub.
Then you can import the project into Android Studio, and hopefully you should be able to build and edit the code for yourself!

## First Time Using Android Studio?
There are a few more steps if you are opening Android Studio and trying to edit the project for the first time.

### Accept the terms for the SDK packages
You need to accept the terms for the SDK Packages that are required by Android Studio. Sometimes the accept prompt is not shown the first time your open Android Studio, and this means that you cannot ever accept them unless by using one of the following methods:

Run these commands in Command Prompt. This will launch the SDKManager command line tool and allow you to accept the terms by typing "y" at the prompts.

`cd %LOCALAPPDATA%\Android\Sdk\tools\bin`
`sdkmanager.bat --licenses`

Does this cause a Java exception? In this case, you will need to reinstall the packages since the current ones are corrupted.

- Open SDKManager within Android Studio by going to Tools > SDKManager.
- Open the SDK Tools tab (middle one).
- Remember which packages are ticked (you'll need to retick them after uninstall).
- Untick all of the checked packages.
- Hit apply and wait for the uninstall.
- Once it is done, retick **only** the items that you unticked.
- Hit apply and wait for the install.
- Once it is done, close SDKManager.

Now, you need to move onto the next step, which is:

### Do a Gradle Sync

Gradle Syncs are really easy to do.

- In Android Studio, click File > Sync Project with Gradle Files.
- Wait for Gradle Sync.
- Hopefully it will work now!

Those are all the steps needed to get this project up and running. Hopefully it works!