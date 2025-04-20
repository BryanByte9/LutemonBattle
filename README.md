General description of all features: 
In our project, we meet all the basic requirements:  
We can Create Lutemon in Creation UI, with reminder if anything is wrong. In Creation UI the 
user will see different images of different Lutemons. We set a different class called 
AttributeOption instead of Lutemon class because we want to show the attribute image of 
Lutemons instead of the actual images of Lutemons. That will be shown in Creation UI.  

In addition, we use Recyclerview to present Lutemons that we can create in Creation UI. We can 
choose 2 Lutemons every time to battle with each other. More than the basic requirements, we set 
a random attack bonus for each Lutemons. Besides, the user can check the current HP and attack 
according to the text description we set. We set health bars and data to show current HP to make 
the battle process as visual as possible. 

Instead of automatically going through the battle process, the battle algorithm will go on only 
when user Click the button “Attack”. The user can end the battle at any time as well.  

In Lutemon class, we set our own custom feature, the speed. We use speed to decide which 
Lutemon attack first. The lost Lutemon will return to Home and return to initial state (The Hp will 
restore, but the experience will return to 0). The winner can gain 1 experience.  

The user can view the battle area and training area even if no Lutemon is selected to battle or train.  

On the home page, the user can choose 1 Lutemon to train each time. The images of Lutemons 
will be shown in training area and battle area. In home page, we set recyclerview to present all the 
Lutemons with their features.  

On home page, we also set a file with serialization to store data to a file. The user can load them 
when they are needed.  

In Statistics page, total number of training and battle and number of Lutemons will be shown. We 
also set a recyclerview to present all Lutemon own statistics, which shows numbers of training, 
battle, win and lost.

Thanks for using this program:)
