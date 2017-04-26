 ArchMVVM
============

An architectural tool for Android based on MVVM pattern.

###What's MVVM?  
Model-view-viewmodel (MVVM) is a software architectural pattern. [Wiki](https://en.wikipedia.org/wiki/Model-view-viewmodel)  
The MVVM pattern helps you to cleanly separate the business 
and presentation logic of your application from its user interface (UI). 
Maintaining a clean separation between application logic and UI 
helps to address numerous development and design issues and 
can make your application much easier to test, maintain, and evolve. 
It can also greatly improve code re-use opportunities and 
allows developers and UI designers to more easily collaborate 
when developing their respective parts of the application.  
In the MVVM pattern, the view encapsulates the UI and any UI logic, 
the view model encapsulates presentation logic and state, 
and the model encapsulates business logic and data. The view interacts 
with the view model through data binding, commands, 
and change notification events. The view model queries, observes, 
and coordinates updates to the model, converting, validating, 
and aggregating data as necessary for display in the view.  
[Implementing the MVVM Pattern](https://msdn.microsoft.com/en-us/library/gg405484.aspx)
  
![The MVVM classes and their interactions](images/mvvm-classes-and-interactions.png)  

###Model
Business Logic and Data

* NetWork
* Database crud
* File I/O
* SharedPreferences

###View
UI

* XML Layout
* Styles

UI Logic(Code Behind)

* Activity/Fragment
* Showing Dialogs, Toasts, Snackbars
* Starting Activities
* Handling Menu
* Handling permissions
* Other Android specific stuff & methods which require reference to the Activity Context

###ViewModel
Presentation Logic

* Exposing state (progress, error, etc.)
* Exposing data
* Handling visibility
* Handling Extras & Arguments (Bundle)
* Input validation
* Executing data calls in the Model
* Executing UI commands in the View 

License
-------

    Copyright (c) 2017 Victor Chiu

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
