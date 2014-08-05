var CONSTANTS = {
    MENU_COLLAPSED : true,
    EVENTS : {
        OVERVIEW_ENTITY_NAME_SET : 'exap.overview.entity.name.set'
    },
    CODEMIRROR_LANGUAGES : [
        // 'text/x-csrc', 'text/x-c++src', 'text/x-java', 'text/x-csharp'
        {name : "Java", code : "text/x-java"},
        {name : "C", code : "text/x-csrc"},
        {name : "C++", code : "text/x-c++src"},
        {name : "C#", code : "text/x-csharp"}
    ],
    QUESTION_TYPE : ["OPEN", "CLOSED-SINGLE", "CLOSED-MULTI"],
    EXAM_TYPE : ["OPEN", "CLOSED"]
};
var EVENTS = CONSTANTS.EVENTS;