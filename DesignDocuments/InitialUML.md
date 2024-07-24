```mermaid
---
title: Rick and Morty App
---
classDiagram
    direction LR
    class CharacterApp{
        
 }
    class JFrameView{
        
 }
    class Filter{
        
    }
    class NetUtils{
        
    }
    class Sorter{
        
    }
    class Character{
        
    }
    Character --|> ICharacter: implements
    class ICharacter{
        <<INTERFACE>>
    }
    class DataFormatter{
        
    }
    class DomainXMLWrapper{
        
    }
    class Formats{
        
    }
    class CharacterController{
        
    }
```