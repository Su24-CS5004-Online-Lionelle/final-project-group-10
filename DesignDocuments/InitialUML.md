```mermaid
---
title: Rick and Morty App
---
classDiagram
    direction TB
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
         - app: CharacterApp
         - cList: CharacterList
    }
    class CharacterList{
        
    }
    CharacterApp *-- JFrameView
    CharacterApp *-- CharacterController
    CharacterApp *-- CharacterList
    JFrameView o-- CharacterController: updates
    CharacterController o-- CharacterList: modifies
    CharacterList o-- Character
    CharacterList o-- Filter
    CharacterList o-- DataFormatter
    CharacterList o-- DomainXMLWrapper
    CharacterList o-- NetUtils
    CharacterList o-- Sorter
    CharacterList o-- Formats
    

    style DataFormatter fill:#01FF18
    style CharacterList fill:#01FF18
    style DomainXMLWrapper fill:#01FF18
    style Formats fill:#01FF18
    style NetUtils fill:#01FF18
    style Sorter fill:#01FF18
    style Character fill:#01FF18
    style Filter fill:#01FF18
    style ICharacter fill:#01FF18
    style JFrameView fill: #FF0101
    style CharacterController fill:#0149FF


```