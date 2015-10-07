# Alfred 
**_UNDER CONSTRUCTION_**

## Project Overview
Project Alfred is a java smart home framework. It was originally designed to have a server application running on a Raspberry Pi, and have Android clients connected through a home network.

The purpose is to give a user control over various electrical devices in the home using a mobile device. 

## Early Days
There were two motivating factors behind creating the early version of Alfred:

1. My father-in-law said he couldn't hear his doorbell everywhere in his house, so he wanted some way to get notifications on his phone when someone was at the door
2. My wife and I have young children who nap during the day, and we don't want people ringing the doorbell during the day and waking up our kids

With these two objectives I went to work designing a system that could send notifications over a home network. As I started developing the framework it became clear that the basic framework could be extended to control other components of the house (garage doors, lights, ceiling fans, etc) because they were all fundamentally the same - an electrical device that can be represented by a state (on, off, open, closed, etc).

Upon this realization I shifted the focus of the project into creating a more scaelable framework. Today the project can be used to control almost any state driven device in a house (assuming you have domain knowledge about connecting electrical devices)


## Common API
This project contains the API for project Alfred. Client and server applications can include this API and begin developing applications for interacting with devices around their house

The API includes templates for devices, like a light, a garage door, a doorbell. It also includes messaging and handler interfaces.

## API Usage
