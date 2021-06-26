# Android App Remote Control Joystick

## Table of contents
1. [ Background. ](#back)  
2. [ Prerequisites. ](#pre)  
3. [ Run the app. ](#inst)
4. [ UML. ](#UML)
5. [ Video. ](#video)  

<a name="back"></a>
## Background
The app was designed with the architectural pattern [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel).  
The app is divide to 3 main parts – Model, View, View-Model:  
* The **View** is composed from two parts:
    1. The `XML` file, which is the front view. Doesn’t contain any business logic, and only present.
    2. The `Kotlin` files. There is the `MainActivity` file for the communication with the `XML` file, and it contains all the logic for the communication with the View-Model. 
       There is the `Joystick` file that draw the joystick and has the responsibility to control the view of the joystick. Those files have data-binding to the `XML`, and they get the data to pass to the view model. The View communicates with the view model by call functions with the data that the user inserts.  
* The **View-Model** is a file that just pass the data to the model. 
It doesn’t calculate any data, or try to get any new data in any other way. 
The View call the View-Model functions when necessary and the View-Model call the appropriate functions in the Model.  
* The **Model** is responsible to the communication with the `FlightGear` (connect to the right port and send data). 
The Model was designed with the design pattern `Active Object`.
The connection to the Port happens in one thread, and the output requests (send data) happens in another thread.
Notice that the model doesn’t return any result or answer to the View-Model, and therefore also the View-Model doesn’t return something to the View, so all the app is One-Way-App from the View to the Model.
<a name="pre"></a>
## Prerequisites
**Download and Install Flightgear:**  
1. Click [here](https://www.flightgear.org/download/) to download the FlightGear and then install it.  
2. Open the simulator, click on settings and then in the Additional Settings tap the following: 
>```--telnet=socket,in,10,127.0.0.1,6400,tcp```  

**Download and Install Android Studio:**  
* Click [here](https://developer.android.com/studio) to download Android Studio and then install it. (If you prefer **Intellij** click [here](https://www.jetbrains.com/idea/download/))  

**Getting your IP**
* Later you'll need to insert your IP adress to run the app, to get it do the following:
```
1. Open the cmd  
2. Run the command 'ipconfig'  
3. In the line of IPV4 Adress, get your IP
```

<a name="inst"></a>
## Run the app
1. Open the FlightGear
2. Click on ***Fly!***
3. Run the command on the terminal `git clone https://github.com/MickaelAlbo/Android-App-Control-Joystick.git`  
4. Open the Android Studio/Intellij and import the project from the cloning destination path 
5. Run the app  
6. Insert ***6400*** in the ***port field***
7. Insert your **IP** adress in the ***IP field*** and click on ***Connect!***
8. When you want to disconnect, click on ***Disconnect!*** (You can also exit by clicking ***Exit app!***)  

**The app:**  

![image](https://user-images.githubusercontent.com/71727260/123522238-c1e6e200-d6c4-11eb-94cc-4061b4dba524.png)

<a name="UML"></a>
## UML  
To see the UML of the project click [here](https://github.com/MickaelAlbo/Android-App-Control-Joystick/blob/master/UML.pdf).

## Video
<a name="Video"></a>
