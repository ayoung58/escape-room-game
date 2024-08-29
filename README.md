# CPSC 210 Project Proposal

## Overview

The project that I am proposing is a simple *escape room game*.
This game will include puzzles and codes that the player will
have to solve to escape the room or to find something valuable
so that they can beat the game. There will be 
a story to accompany the game elements so that the game is more 
interesting.

In the graphical user interface, players will be able to move
around and also interact with various elements. In a console based
version, they'll have to mostly rely on text and typing to
tell the program what to interact with and how. Movement will
be quite limited in this version.

### Some possible elements in the game

- Finding keys to open locks or doors
- Notes that have clues on them
- Safes or combination locks that require a code to open
- Lights or switches that have to be turned on in a certain order
- Computer passwords or phone numbers, with clues for the correct one hidden elsewhere

This idea is also fairly flexible in my opinion, as it can be 
fairly simple (with less/easier to program elements) if production
is not running as smoothly as it could be. If production is 
going well, then harder and possibly more intricate elements
or design can be implemented.

### Who will use it

The target audience is anyone who enjoys solving puzzles or playing indie-like
games. It's also for people who have spare time on their hands and would like
to try exercising their mind for a while. It may also be for those that like
mystery, as the story of this game will likely involve some elements in that vein 
if it is well written.

### Why I'm interested

This project is of great interest to me because I've really
wanted to create my own escape room. I'm very interested in game
design, and this is an exciting way for me to create something
that is fun for others and for myself. 

## User Stories

- As a user, I want to be able to add items (i.e. keys, notes, etc.) to my inventory
- As a user, I want to be able to view the items in my inventory 
- As a user, I want to be able to select items in my inventory 
to view details about a respective item
- As a user, I want to be able to input codes and solutions for a puzzle 
either through text input or through mouse interactions
- As a user, I want to be able to use items and possibly combine them with 
other items or with other elements in the escape room itself
- As a user, I want to be able to move around the room 
and select/interact with different game elements or items in the escape room

- As a user, I want to be able to have the option to save my game state 
(i.e. inventory and explorable items) between actions (when I choose).
- As a user, I want to be able to have the option to load my game state near 
the start of the game and between actions (when I choose).
# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by
clicking on the object that you want to add. If it can be added it will be added and a pop up will say
that it has been added. Otherwise, pop up will not show.
- You can generate the second required action inside the inventory by pressing
either collectable, interactable, or all to filter the items in inventory.
- You can generate another required action inside the inventory by pressing
items inside. This will remove the item. 
- You can locate my visual component by looking at the background image of the
splash start screen.
- You can save the state of my application by clicking the save button during game.
- You can reload the state of my application by clicking the load button during game.

# Phase 4: Task 2
Here is a sample of the events that happened as items were added and removed
from the inventory:

Fri Apr 05 16:33:14 PDT 2024 \
Added Small safe to inventory.

Fri Apr 05 16:33:15 PDT 2024 \
Added Piece 1 to inventory.

Fri Apr 05 16:33:16 PDT 2024 \
Added Piece 2 to inventory.

Fri Apr 05 16:33:17 PDT 2024 \
Removed Piece 2 from inventory.

Fri Apr 05 16:33:17 PDT 2024 \
Combinable filter filtered out small safe.

Fri Apr 05 16:33:17 PDT 2024 \
Combinable filter filtered to show a piece.

Fri Apr 05 16:33:18 PDT 2024 \
Interactable filter filtered to show small safe.

Fri Apr 05 16:33:18 PDT 2024 \
Interactable filter filtered out a piece.

Fri Apr 05 16:33:20 PDT 2024 \
Removed Small safe from inventory.

As can be seen, the item's name is shown as well as the 
action that was performed on it. 

# Phase 4: Task 3

There are definitely quite a few things that can be changed based on the design
shown in the UML diagram. One of the first things that can be noticed is that there is a lot of repetition 
when it comes to objects. For example, both the GUI and text based styles require instantiation
of virtually the same objects \(with the GUI version requiring a few more\). It may 
be more beneficial to consider the use a singleton pattern, as we only need one global instance 
for most objects throughout the game. It may also be beneficial to consider having one instance 
passed around different methods, in case we really want two or more mostly
unique instances. These methods would ensure that the objects' instances and properties are maintained throughout the program, and each part of the program
would be able to access up-to-date information.  

There are also places where one class is doing the work of two. For example,
Wall 1 is technically both a lockable item and a wall. I would want to separate its functionality
so that it is not as ambiguous as to what its function is. Perhaps I would have a Keyhole class
that extends LockableObjects instead. Also, inventory and explorableObjects have 
very similar \(both have a list of items and a list of items to remove, both are performing similar
functionalities\). Thus, it may be better to create a different class \(though there could be risks of this 
new class taking on too many tasks\), or extract some of the duplicate methods and call these new 
methods from inside each of the original methods instead from explorableObjects and inventory instead.
\