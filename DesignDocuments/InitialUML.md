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
    style DataFormatter fill:#01FF18
    style DomainXMLWrapper fill:#01FF18
    style Formats fill:#01FF18
    style NetUtils fill:#01FF18
    style Sorter fill:#01FF18
    style Character fill:#01FF18
    style Filter fill:#01FF18
    style ICharacter fill:#01FF18
    style JFrameView fill: #FF0101
    style CharacterApp fill: #FF0101
    style CharacterController fill:#0149FF


```