var CONSTANTS = {
        MENU: {
            COLLAPSED : true,
            QUESTION : {
                COLLAPSED : false
            }
        },
        EVENTS: {
            OVERVIEW_ENTITY_NAME_SET: 'exap.overview.entity.name.set',
            CRUD_READ_ENTITIES: 'exap.crud.read.entities'
        },
        CRUD_OPERATIONS: {
            CREATE: 'CREATE',
            READ: 'READ',
            UPDATE: 'UPDATE',
            DELETE: 'DELETE'
        },
        CODEMIRROR_LANGUAGES: [
            // 'text/x-csrc', 'text/x-c++src', 'text/x-java', 'text/x-csharp'
            {name: "Java", code: "text/x-java"},
            {name: "C", code: "text/x-csrc"},
            {name: "C++", code: "text/x-c++src"},
            {name: "C#", code: "text/x-csharp"}
        ],
        QUESTION_TYPE: {
            OPEN: "OPEN",
            CLOSED: "CLOSED-SINGLE",
            CLOSED_MUTLI: "CLOSED-MULTI"
        },
        EXAM_TYPE: ["OPEN", "CLOSED"]
    }
    ;
var EVENTS = CONSTANTS.EVENTS;
var CRUD = CONSTANTS.CRUD_OPERATIONS;